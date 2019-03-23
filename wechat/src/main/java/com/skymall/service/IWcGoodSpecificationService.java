package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.GoodsSpecification;
import com.skymall.vo.GoodsSpecificationWithName;
import com.skymall.vo.wechat.GoodsSpecificationVo;

import java.util.List;
import java.util.Map;

public interface IWcGoodSpecificationService extends IService<GoodsSpecification> {

    List<GoodsSpecificationWithName> getSpecificationWithName(Integer goodId);

    List<GoodsSpecificationVo> queryList(Map map);


}
