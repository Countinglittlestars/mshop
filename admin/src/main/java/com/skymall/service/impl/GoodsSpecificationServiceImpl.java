package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.GoodsSpecificationMapper;
import com.skymall.domain.GoodsSpecification;
import com.skymall.dto.GoodSpecificationQueryDto;
import com.skymall.dto.GoodsSpecQueryDto;
import com.skymall.service.IGoodsSpecificationService;
import com.skymall.vo.admin.GoodSpecificationEntity;
import com.skymall.vo.admin.GoodsSpecificationEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品对应规格表值表 服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service
public class GoodsSpecificationServiceImpl extends ServiceImpl<GoodsSpecificationMapper, GoodsSpecification> implements IGoodsSpecificationService {

    @Resource
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<GoodsSpecificationEntity> queryList(GoodSpecificationQueryDto goodSpecificationQueryDto) {
        List<GoodsSpecificationEntity> list = goodsSpecificationMapper.queryEntityList(goodSpecificationQueryDto);
        return list;
    }

    @Override
    public IPage<GoodSpecificationEntity> queryAll(Page page) {
        return goodsSpecificationMapper.queryAll(page);
    }

    @Override
    public List<GoodSpecificationEntity> queryById(Integer id) {
        return goodsSpecificationMapper.queryById(id);
    }

    @Override
    public IPage<GoodSpecificationEntity> queryByExample(Page page, GoodsSpecQueryDto goodsSpecQueryDto) {
        return goodsSpecificationMapper.queryByExample(page,goodsSpecQueryDto);
    }
}
