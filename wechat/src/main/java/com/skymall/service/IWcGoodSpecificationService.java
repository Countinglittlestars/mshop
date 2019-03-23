package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.GoodsSpecification;
import com.skymall.vo.GoodsSpecificationWithName;

import java.util.List;

public interface IWcGoodSpecificationService extends IService<GoodsSpecification> {

    List<GoodsSpecificationWithName> getSpecificationWithName(Integer goodId);
}
