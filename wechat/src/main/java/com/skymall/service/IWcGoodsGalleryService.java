package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.GoodsGallery;

import java.util.List;

public interface IWcGoodsGalleryService extends IService<GoodsGallery> {

    public GoodsGallery queryById(Integer id);

    public List<GoodsGallery> queryByGoodsId(Integer goodsId);
}
