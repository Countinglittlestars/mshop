package com.skymall.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.skymall.constant.WcConstant;
import com.skymall.service.IWcCartService;
import com.skymall.vo.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/mshop/wc/cart")
public class WcCartController extends AbstractController{


    @Resource
    HttpServletRequest request;

    @Resource
    IWcCartService cartService;

    /**
     * 获取用户所有购物车内容
     * @return
     */
    @RequestMapping
    @PostMapping("/getCart")
    public Object getCart(){
        Integer userId = Integer.valueOf(request.getParameter(WcConstant.LOGIN_USER_KEY));
        Object obj  = cartService.getCart(userId);
        return obj;
    }

    /**
     * 获取购物车信息，所有对购物车的增删改操作，都要重新返回购物车的信息
     */
    @ApiOperation(value = "获取购物车信息")
    @PostMapping("/index")
    public Object index() {
        return Response.success(getCart());
    }


    /**
     * 添加商品到购物车
     *
     */
    @PostMapping(value = "/add")
    public Object add(){
        Integer userId = (Integer) request.getAttribute(WcConstant.LOGIN_USER_KEY);
        JSONObject jsonParam = getJsonRequest();
        cartService.add(jsonParam, userId);

        return Response.success();
    }


    /**
     * 从购物车中删除
     * @return
     */
    @PostMapping(value = "/remove")
    public Object remove(){
        JSONObject jsonParam = getJsonRequest();
        cartService.removeCart(jsonParam);

        return Response.success();
    }







}
