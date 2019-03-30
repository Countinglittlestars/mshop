package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.GoodsSpecificationMapper;
import com.skymall.dao.ProductMapper;
import com.skymall.domain.GoodsSpecification;
import com.skymall.domain.Product;
import com.skymall.dto.ProductQueryDto;
import com.skymall.service.IGoodsSpecificationService;
import com.skymall.service.IProductService;
import com.skymall.utils.StringUtils;
import com.skymall.vo.admin.ProductEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Resource
    ProductMapper productMapper;

    @Resource
    IGoodsSpecificationService goodsSpecificationService;

    /**
     * 根据goodsId或者商品名称查找对应的产品信息
     * @param page
     * @param map
     * @return
     */
    @Override
    public IPage<ProductEntity> queryList(IPage page, ProductQueryDto map) {
        IPage<ProductEntity> pageEntity = productMapper.queryEntityList(page, map);
        List<ProductEntity> list = pageEntity.getRecords();
        List<ProductEntity> result = new ArrayList<>();
        //翻译产品规格
        if (null != list && list.size() > 0) {
            for (ProductEntity item : list) {
                String specificationIds = item.getGoodsSpecificationIds();
                String specificationValue = "";
                if (!StringUtils.isNullOrEmpty(specificationIds)) {
                    String[] arr = specificationIds.split("_");

                    for (String goodsSpecificationId : arr) {
                        GoodsSpecification entity = goodsSpecificationService.getById(goodsSpecificationId);
                        if (null != entity) {
                            specificationValue += entity.getValue() + "；";
                        }
                    }
                }
                item.setSpecificationValue(item.getGoodsName() + " " + specificationValue);
                result.add(item);
            }
        }
        IPage<ProductEntity> resultPage = new Page<>();
        resultPage.setRecords(result);
        resultPage.setTotal(result.size());
        resultPage.setPages(pageEntity.getPages());
        resultPage.setSize(pageEntity.getSize());
        resultPage.setTotal(pageEntity.getTotal());
        resultPage.setCurrent(pageEntity.getCurrent());
        return resultPage;
    }

}
