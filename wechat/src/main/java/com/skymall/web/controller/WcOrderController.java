package com.skymall.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skymall.annotation.IgnoreAuth;
import com.skymall.constant.WcConstant;
import com.skymall.domain.Order;
import com.skymall.service.IWcOrderGoodsService;
import com.skymall.service.IWcOrderService;
import com.skymall.utils.ApiPageUtils;
import com.skymall.utils.BeanUtils;
import com.skymall.utils.Query;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.OrderGoodsVo;
import com.skymall.vo.wechat.OrderVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mshop/wc/order")
public class WcOrderController extends AbstractController{

    @Resource
    IWcOrderService orderService;


    @Resource
    IWcOrderGoodsService orderGoodsService;

    /**
     * 获取订单详情
     */
    @ApiOperation(value = "获取订单详情")
    @PostMapping("detail")
    public Object detail(Integer orderId) {
        Map resultObj = new HashMap();
        //
        OrderVo orderInfo = orderService.queryObject(orderId);
        if (null == orderInfo) {
            return Response.error(400, "订单不存在");
        }
        Map orderGoodsParam = new HashMap();
        orderGoodsParam.put("order_id", orderId);
        //订单的商品
        List<OrderGoodsVo> orderGoods = orderGoodsService.queryList(orderGoodsParam);
        //订单最后支付时间
        if (orderInfo.getOrderStatus() == 0) {
            // if (moment().subtract(60, 'minutes') < moment(orderInfo.add_time)) {
//            orderInfo.final_pay_time = moment("001234", "Hmmss").format("mm:ss")
            // } else {
            //     //超过时间不支付，更新订单状态为取消
            // }
        }

        //订单可操作的选择,删除，支付，收货，评论，退换货
        Map handleOption = orderInfo.getHandleOption();
        //
        resultObj.put("orderInfo", orderInfo);
        resultObj.put("orderGoods", orderGoods);
        resultObj.put("handleOption", handleOption);
//        if (!StringUtils.isEmpty(orderInfo.getShippingCode()) && !StringUtils.isEmpty(orderInfo.getShippingNo())) {
            // 快递
//            List Traces = apiKdniaoService.getOrderTracesByJson(orderInfo.getShippingCode(), orderInfo.getShippingNo());
//            resultObj.put("shippingList", Traces);
//        }
        return Response.success(resultObj);
    }

    
    @ApiOperation(value = "订单首页")
    @IgnoreAuth
    @PostMapping("index")
    public Object index() {
        //
        return Response.success("");
    }


    /**
     * 获取订单列表
     */
    @ApiOperation(value = "订单提交")
    @PostMapping("submit")
    public Object submit() {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();

        Map resultObj = null;
        try {
            resultObj = orderService.submit(getJsonRequest(), userId);
            if (null != resultObj) {


                Object data = resultObj.get("data");
                Map<String, Object> obj = new HashMap<String, Object>();
                obj.put("errno", MapUtils.getInteger(resultObj, "errno"));
                obj.put("errmsg", MapUtils.getString(resultObj, "errmsg"));
                if (data != null)
                    obj.put("data", data);
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.error("提交失败");
    }

    /**
     * 获取订单列表
     */
    @ApiOperation(value = "取消订单")
    @PostMapping("cancelOrder")
    public Object cancelOrder(Integer orderId) {
        try {
            OrderVo orderVo = orderService.queryObject(orderId);
            if (orderVo.getOrderStatus() == 300) {
                return Response.error("已发货，不能取消");
            } else if (orderVo.getOrderStatus() == 301) {
                return Response.error("已收货，不能取消");
            }
            // 需要退款
            if (orderVo.getPayStatus() == 2) {
//                WechatRefundApiResult result = WechatUtil.wxRefund(orderVo.getId().toString(),
//                        0.01, 0.01);
//                if (result.getResult_code().equals("SUCCESS")) {
                    if (orderVo.getOrderStatus() == 201) {
                        orderVo.setOrderStatus(401);
                    } else if (orderVo.getOrderStatus() == 300) {
                        orderVo.setOrderStatus(402);
                    }
                    orderVo.setPayStatus(4);
                    Order order = new Order();
                    BeanUtils.mapping(orderVo, order);
                    orderService.updateById(order);
                    return Response.success("取消成功");

            } else {
                orderVo.setOrderStatus(101);
                Order order = new Order();
                BeanUtils.mapping(orderVo, order);
                orderService.updateById(order);
                return Response.success("取消成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.error("提交失败");
    }

    /**
     * 确认收货
     */
    @ApiOperation(value = "确认收货")
    @PostMapping("confirmOrder")
    public Object confirmOrder(Integer orderId) {
        try {
            OrderVo orderVo = orderService.queryObject(orderId);
            orderVo.setOrderStatus(301);
            orderVo.setShippingStatus(2);
            orderVo.setConfirmTime(new Date());
            Order order = new Order();
            BeanUtils.mapping(orderVo, order);
            orderService.updateById(order);
            return Response.success("确认收货成功");
        } catch (Exception e) {

            e.printStackTrace();
        }
        return Response.error("提交失败");
    }

    @ApiOperation(value = "修改订单")
    @PostMapping("updateSuccess")
    public Object updateSuccess(Integer orderId) {
        OrderVo orderInfo = orderService.queryObject(orderId);
        if (orderInfo == null) {
            return Response.error("订单不存在");
        } else if (orderInfo.getOrderStatus() != 0) {
            return Response.error("订单状态不正确orderStatus" + orderInfo.getOrderStatus() + "payStatus" + orderInfo.getPayStatus());

        }

        orderInfo.setId(orderId);
        orderInfo.setPayStatus(2);
        orderInfo.setOrderStatus(201);
        orderInfo.setShippingStatus(0);
        orderInfo.setPayTime(new Date());
        Order order = new Order();
        BeanUtils.mapping(orderInfo, order);
        Boolean flag = orderService.updateById(order);
        if (flag == true) {
            return Response.success("修改订单成功");
        } else {
            return Response.error("修改订单失败");
        }
    }


    /**
     * 获取订单列表
     */
    @ApiOperation(value = "获取订单列表")
    @PostMapping("list")
    public Object list(
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        //
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        Map params = new HashMap();
        params.put("user_id", userId);
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "id");
        params.put("order", "asc");
        //查询列表数据
        Query query = new Query(params);
        //-----------------------------------------------------------------------------需要补充写sql语句
        List<OrderVo> orderEntityList = orderService.queryList(query);
        int total = orderService.count(new QueryWrapper<Order>().lambda().eq(Order::getUserId, userId));

        ApiPageUtils pageUtil = new ApiPageUtils(orderEntityList, total, query.getLimit(), query.getPage());

        for (OrderVo item : orderEntityList) {
            Map orderGoodsParam = new HashMap();
            orderGoodsParam.put("order_id", item.getId());
            //订单的商品-----------------------------------------------------------------------------需要补充写sql语句
            List<OrderGoodsVo> goodsList = orderGoodsService.queryList(orderGoodsParam);
            Integer goodsCount = 0;
            for (OrderGoodsVo orderGoodsEntity : goodsList) {
                goodsCount += orderGoodsEntity.getNumber();
                item.setGoodsCount(goodsCount);
            }
        }
        return Response.success(pageUtil);
    }


}
