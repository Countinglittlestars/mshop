package com.skymall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Order;
import com.skymall.vo.wechat.OrderVo;

import java.util.List;
import java.util.Map;

public interface IWcOrderService extends IService<Order> {
    public Map<String, Object> submit(JSONObject jsonParam, Integer userId);

    OrderVo queryObject(Integer id);


    List<OrderVo> queryList(Map map);

    Integer queryTotal(Map map);
}
