package com.skymall.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.skymall.constant.WcConstant;
import com.skymall.utils.GuavaCacheUtil;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.BuyGoodsVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mshop/wc/buy")
public class WcBuyController extends AbstractController{

    @Value("SHOP_CACHE_NAME")
    String shopCacheName;

    @ApiOperation(value = "商品添加")
    @PostMapping("/add")
    public Object addBuy() {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Integer number = jsonParam.getInteger("number");
        BuyGoodsVo goodsVo = new BuyGoodsVo();
        goodsVo.setGoodsId(goodsId);
        goodsVo.setProductId(productId);
        goodsVo.setNumber(number);
        GuavaCacheUtil.setKey(shopCacheName+userId, goodsVo);
        return Response.success("添加成功");
    }


}
