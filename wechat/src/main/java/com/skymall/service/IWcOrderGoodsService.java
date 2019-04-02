package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.OrderGoods;
import com.skymall.vo.wechat.OrderGoodsVo;

import java.util.List;
import java.util.Map;

public interface IWcOrderGoodsService extends IService<OrderGoods> {
    List<OrderGoodsVo> queryList(Map map);
}
