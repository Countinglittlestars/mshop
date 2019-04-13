package com.skymall.web.controller;

import com.skymall.domain.Order;
import com.skymall.service.IWcOrderService;
import com.skymall.utils.BeanUtils;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.OrderVo;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping(value = "/mshop/wc/pay")
public class WcPayController {
    private Logger logger = Logger.getLogger(getClass());

    @Resource
    IWcOrderService orderService;


    /**
     */
    @ApiOperation(value = "跳转")
    @PostMapping("index")
    public Object index() {
        //
        return Response.success("");
    }

    @ApiOperation(value = "获取支付的请求参数")
    @PostMapping("prepay")
    public Object payPrepay(Integer orderId) {
        OrderVo orderInfo = orderService.queryObject(orderId);
        if (null == orderInfo) {
            return Response.error(400, "订单已取消");
        }

//        if (orderInfo.getPayStatus() != 0) {
//            return Response.error(400, "订单已支付，请不要重复操作");
//        }
//        orderInfo.setPayStatus(1);
        Order order = new Order();
        BeanUtils.mapping(orderInfo, order);
        orderService.updateById(order);
        return Response.success();
    }

    /**
     * 微信查询订单状态
     */
    @ApiOperation(value = "查询订单状态")
    @PostMapping("query")
    public Object orderQuery(Integer orderId) {
        if (orderId == null) {
            return Response.error("订单不存在");
        }
        // 更改订单状态
        // 业务处理
        Order orderInfo = new Order();
        orderInfo.setId(orderId);
//        orderInfo.setPayStatus(2);
        orderInfo.setOrderStatus(201);
        orderInfo.setShippingStatus(0);
//        orderInfo.setPayTime(new Date());
        orderService.updateById(orderInfo);
        return Response.success("支付成功");
    }

    @PostMapping("paySuccess")
    public Object paySuccess(Integer orderId){
        if(orderId == null){
            return Response.error("支付失败，没找到对应的订单");
        }
        //修改状态
        Order orderInfo = orderService.getById(orderId);
//        orderInfo.setPayStatus(2);
        orderInfo.setOrderStatus(201);
        orderInfo.setShippingStatus(0);
//        orderInfo.setPayTime(new Date());
        Boolean flag = orderService.updateById(orderInfo);

        if(flag == false) return Response.error("支付失败");
        return Response.success();
    }






}
