package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.GoodsMapper;
import com.skymall.domain.*;
import com.skymall.dto.GoodAddDto;
import com.skymall.dto.GoodQueryDto;
import com.skymall.service.*;
import com.skymall.utils.BeanUtils;
import com.skymall.vo.CommonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodService {

    @Resource
    IAttributeService  attributeService;

    @Resource
    IAttributeCategoryService attributeCategoryService;

    @Resource
    IGoodsAttributeService goodsAttributeService;

    @Resource
    IGoodsGalleryService goodsGalleryService;

    @Resource
    GoodsMapper goodsMapper;



    @Override
    public Object queryByPage(GoodQueryDto goodQueryDto, Integer pageNum, Integer pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(goodQueryDto.getName() != null) {
            queryWrapper.like("name", goodQueryDto.getName());
        }
        if(goodQueryDto.getIsOnSale() != null) {
            queryWrapper.eq("is_on_sale", goodQueryDto.getIsOnSale());
        }
        if(goodQueryDto.getBrandId() != null) {
            queryWrapper.eq("brand_id", goodQueryDto.getBrandId());
        }
        if(goodQueryDto.getGoodsSn() != null) {
            queryWrapper.eq("goods_sn", goodQueryDto.getGoodsSn());
        }
        if(goodQueryDto.getIsHot() != null) {
            queryWrapper.eq("is_hot", goodQueryDto.getIsHot());
        }
        if(goodQueryDto.getIsNew() != null) {
            queryWrapper.eq("is_new", goodQueryDto.getIsNew());
        }
        Page page = new Page(pageNum, pageSize);
        IPage pager = goodsMapper.selectPage(page, queryWrapper);
        return pager;
    }

    @Override
    public Object addGood(GoodAddDto goodAddDto) {
        Goods good = new Goods();
        BeanUtils.mapping(goodAddDto, good);
        good.setIsDelete(false);
        //1. 插入一条最基本的数据的
        goodsMapper.insert(good);
        //2. 然后根据序列号，查找对应的商品，再改变attribute等内容
        Goods newGood = goodsMapper.selectOne( new QueryWrapper<Goods>().lambda().eq(Goods::getGoodsSn, goodAddDto.getGoodsSn()));
        //2.1 添加Goods_Attribute数据
        List<Attribute> attributeList = goodAddDto.getAttributeList();
        attributeList.forEach(e -> {
            GoodsAttribute goodsAttribute = new GoodsAttribute();
            goodsAttribute.setGoodsId(newGood.getId());
            goodsAttribute.setAttributeId(e.getId());
            goodsAttribute.setValue(e.getValue());
            goodsAttributeService.save(goodsAttribute);
        });
        //2.2 添加Goods_gallary数据
        List<String> gallarys = goodAddDto.getPics();
        gallarys.forEach(e->{
            GoodsGallery goodsGallery = new GoodsGallery();
            goodsGallery.setGoodsId(newGood.getId()).setImgUrl(e.toString());
            goodsGalleryService.save(goodsGallery);
        });

        return newGood.getId();
    }
}
