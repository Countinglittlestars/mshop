package com.skymall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.gson.JsonObject;
import com.skymall.domain.Cart;
import com.skymall.vo.wechat.CartVo;

import java.util.List;
import java.util.Map;

public interface IWcCartService extends IService<Cart> {

    Object getCart(Integer userId);

    Object add(JSONObject jsonObject, Integer userId);

    Object removeCart(JSONObject jsonObject);
    public List<CartVo> queryList(Map<String, Object> map);
}
