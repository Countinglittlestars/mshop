package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Goods;
import com.skymall.utils.ApiPageUtils;
import com.skymall.web.dto.requestDto.GoodQueryReqDto;

import java.util.List;

public interface IWcGoodService extends IService<Goods> {

    public List<Goods> selectNewGoods();

    public List<Goods> selectHotGoods();

    public ApiPageUtils queryAll(GoodQueryReqDto goodQueryReqDto);

    public Goods queryById(Integer id);
}
