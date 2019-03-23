package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Goods;
import com.skymall.utils.ApiPageUtils;
import com.skymall.web.dto.requestDto.GoodQueryReqDto;

import java.util.List;
import java.util.Map;

public interface IWcGoodService extends IService<Goods> {

    List<Goods> selectNewGoods();

    List<Goods> selectHotGoods();

    ApiPageUtils queryAll(GoodQueryReqDto goodQueryReqDto);

    Goods queryById(Integer id);

    Map detail(Integer id);

}
