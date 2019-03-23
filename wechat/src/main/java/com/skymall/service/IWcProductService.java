package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Product;
import com.skymall.vo.wechat.ProductVo;

import java.util.List;

public interface IWcProductService extends IService<Product> {

    public List<ProductVo> queryList(Integer goodsId);
}
