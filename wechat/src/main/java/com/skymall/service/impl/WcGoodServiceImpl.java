package com.skymall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.skymall.dao.GoodsMapper;
import com.skymall.domain.Category;
import com.skymall.domain.Goods;
import com.skymall.domain.GoodsGallery;
import com.skymall.domain.GoodsSpecification;
import com.skymall.service.IWcCatagoryService;
import com.skymall.service.IWcGoodService;
import com.skymall.service.IWcGoodSpecificationService;
import com.skymall.service.IWcGoodsGalleryService;
import com.skymall.utils.ApiPageUtils;
import com.skymall.utils.WrapperUtil;
import com.skymall.vo.GoodsSpecificationWithName;
import com.skymall.web.dto.requestDto.GoodQueryReqDto;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WcGoodServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IWcGoodService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private IWcGoodsGalleryService goodsGalleryService;
    @Resource
    private IWcCatagoryService wcCatagoryService;

    @Resource
    private IWcGoodSpecificationService goodSpecificationService;

    @Override
    public List<Goods> selectNewGoods() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Goods::getIsNew, 1);
        return list(queryWrapper);


    }

    @Override
    public List<Goods> selectHotGoods() {
        QueryWrapper entityWrapper = new QueryWrapper();
        entityWrapper.eq("is_hot",1);
        entityWrapper.eq("is_delete", 0);
        PageHelper.startPage(0, 3, false);
        List<Goods> hotGoods = goodsMapper.selectList(entityWrapper);
        return hotGoods;
    }

    @Override
    public ApiPageUtils queryAll(GoodQueryReqDto goodQueryReqDto) {
        goodQueryReqDto.setIsOnSale(1);
        goodQueryReqDto.setIsDelete(0);
        QueryWrapper entityWrapper = new QueryWrapper();
        Page<Goods> page = new Page<>(goodQueryReqDto.getPage(), goodQueryReqDto.getSize());
        WrapperUtil.wrap(entityWrapper, goodQueryReqDto);
        entityWrapper.orderByAsc("id");

        List<Goods> goodsList = goodsMapper.selectPage(page, entityWrapper).getRecords();
        Category currentCategory = wcCatagoryService.selectById(goodQueryReqDto.getCategoryId());

        //包装返回的数据
        ApiPageUtils goodsData = new ApiPageUtils();
        goodsData.setCount(goodsList.size());
        goodsData.setCurrentPage(goodQueryReqDto.getPage());
        goodsData.setGoodsList(goodsList);
        goodsData.setFilterCategory(currentCategory);
        goodsData.setNumsPerPage(goodQueryReqDto.getSize());
        goodsData.setTotalPages((int) Math.ceil((double) goodsList.size() / goodQueryReqDto.getSize()));
        return goodsData;
    }

    @Override
    public Goods queryById(Integer id) {
        Goods goods = goodsMapper.selectById(id);
        return goods;
    }

    @Override
    public Map detail(Integer id) {

        Map<String, Object> result = new HashMap<>();
        //1. 获取商品信息
        Goods goods = goodsMapper.selectById(id);

        //2. 获取展示图片信息
        GoodsGallery goodsGallery = goodsGalleryService.queryByGoodsId(id);

        //3. 获取"商品-规格"信息
        List<GoodsSpecificationWithName> goodsSpecifications = goodSpecificationService.getSpecificationWithName(id);


        //4. 按照规格名称分组的规格信息
        List<Map> specificationList = new ArrayList();
        //按规格名称分组
        for (int i = 0; i < goodsSpecifications.size(); i++) {
            GoodsSpecificationWithName specItem = goodsSpecifications.get(i);
            //
            List<GoodsSpecificationWithName> tempList = null;
            for (int j = 0; j < specificationList.size(); j++) {
                if (specificationList.get(j).get("specificationDd").equals(specItem.getSpecificationId())) {
                    tempList = (List<GoodsSpecificationWithName>) specificationList.get(j).get("valueList");
                    break;
                }
            }
            //
            if (null == tempList) {
                Map temp = new HashMap();
                temp.put("specificationId", specItem.getSpecificationId());
                temp.put("name", specItem.getName());
                tempList = new ArrayList();
                tempList.add(specItem);
                temp.put("valueList", tempList);
                specificationList.add(temp);
            } else {
                for (int j = 0; j < specificationList.size(); j++) {
                    if (specificationList.get(j).get("specificationId").equals(specItem.getSpecificationId())) {
                        tempList = (List<GoodsSpecificationWithName>) specificationList.get(j).get("valueList");
                        tempList.add(specItem);
                        break;
                    }
                }
            }
        }
        //4.1 查看对应的Product信息



        //5. 获取参数信息



        //6. 是否加入收藏

        //7. 评论信息


        result.put("info", goods);
        result.put("gallery", goodsGallery);
        return null;
    }


}
