package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.GoodsGallery;

public interface IWcGoodsGalleryService extends IService<GoodsGallery> {

    public GoodsGallery queryById(Integer id);

    public GoodsGallery queryByGoodsId(Integer goodsId);
}
