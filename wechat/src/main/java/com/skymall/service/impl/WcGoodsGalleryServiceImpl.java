package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.GoodsGalleryMapper;
import com.skymall.domain.GoodsGallery;
import com.skymall.service.AbstractService;
import com.skymall.service.IWcGoodsGalleryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WcGoodsGalleryServiceImpl extends ServiceImpl<GoodsGalleryMapper, GoodsGallery> implements IWcGoodsGalleryService  {

    @Resource
    GoodsGalleryMapper goodsGalleryMapper;

    @Override
    public GoodsGallery queryById(Integer id){
        QueryWrapper entityWrapper = new QueryWrapper();

        GoodsGallery goodsGallery = goodsGalleryMapper.selectById(id);
        return goodsGallery;
    }

    @Override
    public List<GoodsGallery> queryByGoodsId(Integer goodsId) {
       List<GoodsGallery> result = goodsGalleryMapper.selectList(new QueryWrapper<GoodsGallery>().lambda().eq(GoodsGallery::getGoodsId, goodsId));
        return result;
    }


}
