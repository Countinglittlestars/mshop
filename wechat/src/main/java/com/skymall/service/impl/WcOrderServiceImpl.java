package com.skymall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AddressMapper;
import com.skymall.dao.CartMapper;
import com.skymall.dao.OrderGoodsMapper;
import com.skymall.dao.OrderMapper;
import com.skymall.domain.Address;
import com.skymall.domain.Order;
import com.skymall.domain.OrderGoods;
import com.skymall.exception.ApiRRException;
import com.skymall.service.IWcOrderService;
import com.skymall.service.IWcProductService;
import com.skymall.utils.BeanUtils;
import com.skymall.utils.CommonUtil;
import com.skymall.utils.GuavaCacheUtil;
import com.skymall.utils.WrapperUtil;
import com.skymall.vo.wechat.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class WcOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IWcOrderService {

    @Value("SHOP_CACHE_NAME")
    String shopCacheName;

    @Resource
    AddressMapper addressMapper;

    @Resource
    CartMapper cartMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderGoodsMapper orderGoodsMapper;

    @Resource
    IWcProductService productService;

    @Transactional
    public Map<String, Object> submit(JSONObject jsonParam, Integer userId) {
        Map<String, Object> resultObj = new HashMap<String, Object>();

//        Integer couponId = jsonParam.getInteger("couponId");
        String type = jsonParam.getString("type");
//        String postscript = jsonParam.getString("postscript");
//        AddressVo addressVo = jsonParam.getObject("checkedAddress",AddressVo.class);
//        AddressVo addressVo = apiAddressMapper.queryObject(jsonParam.getInteger("addressId"));
        Address addressVo = addressMapper.selectById(jsonParam.getInteger("addressId"));
        Integer freightPrice = 0;

        // * 获取要购买的商品
        List<CartVo> checkedGoodsList = new ArrayList<>();
        BigDecimal goodsTotalPrice = new BigDecimal(0);
        if (type.equals("cart")) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("user_id", userId);
            param.put("session_id", 1);
            param.put("checked", 1);
            checkedGoodsList = cartMapper.queryList(param);
            if (null == checkedGoodsList) {
                resultObj.put("errno", 400);
                resultObj.put("errmsg", "请选择商品");
                return resultObj;
            }
            //统计商品总价
            goodsTotalPrice = new BigDecimal(0.00);
            for (CartVo cartItem : checkedGoodsList) {
                goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            }
        } else {
            BuyGoodsVo goodsVo = (BuyGoodsVo) GuavaCacheUtil.getKey(shopCacheName+userId);
            ProductVo productInfo = productService.queryObject(goodsVo.getProductId());
            //计算订单的费用
            //商品总价
            goodsTotalPrice = productInfo.getRetailPrice().multiply(new BigDecimal(goodsVo.getNumber()));
        }

        //订单价格计算
        BigDecimal orderTotalPrice = goodsTotalPrice.add(new BigDecimal(freightPrice)); //订单的总价

//        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额
        BigDecimal actualPrice = orderTotalPrice;
        Long currentTime = System.currentTimeMillis() / 1000;

        //
        OrderVo orderInfo = new OrderVo();
        orderInfo.setOrderSn(CommonUtil.generateOrderNumber());
        orderInfo.setUserId(userId);
        //收货地址和运费
        orderInfo.setConsignee(addressVo.getUserName());
        orderInfo.setMobile(addressVo.getTelNumber());
        orderInfo.setCountry(addressVo.getNationalCode());
        orderInfo.setProvince(addressVo.getProvinceName());
        orderInfo.setCity(addressVo.getCityName());
        orderInfo.setDistrict(addressVo.getCountyName());
        orderInfo.setAddress(addressVo.getDetailInfo());
        //
//        orderInfo.setFreightPrice(freightPrice);
        //留言
//        orderInfo.setPostscript(postscript);
        //使用的优惠券
//        orderInfo.setCoupon_id(couponId);
//        orderInfo.setCoupon_price(couponPrice);
        orderInfo.setAddTime(new Date());
//        orderInfo.setGoodsPrice(goodsTotalPrice);
        orderInfo.setOrderPrice(orderTotalPrice);
//        orderInfo.setActualPrice(actualPrice);
        // 待付款
        orderInfo.setOrderStatus(0);
        orderInfo.setShippingStatus(0);
//        orderInfo.setPayStatus(0);
//        orderInfo.setShippingId(0);
//        orderInfo.setShippingFee(new BigDecimal(0));
//        orderInfo.setIntegral(0);
//        orderInfo.setIntegralMoney(new BigDecimal(0));
//        if (type.equals("cart")) {
//            orderInfo.setOrderType("1");
//        } else {
//            orderInfo.setOrderType("4");
//        }

        //开启事务，插入订单信息和订单商品
        Order order = new Order();
        BeanUtils.mapping(orderInfo, order);
        try {
            int orderId = orderMapper.insert(order);
            Order newOrder = orderMapper.selectOne(new QueryWrapper<Order>().lambda().eq(Order::getOrderSn, order.getOrderSn()));
            Integer newId = newOrder.getId();
            orderInfo.setId(newId);
        }catch (Exception e){
            throw new ApiRRException("订单提交失败", 1);
        }

        //统计商品总价
//        List<OrderGoodsVo> orderGoodsData = new ArrayList<OrderGoodsVo>();
//        for (CartVo goodsItem : checkedGoodsList) {
//
//            OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
//            orderGoodsVo.setOrderId(orderInfo.getId());
//            orderGoodsVo.setGoodsId(goodsItem.getGoodsId());
//            orderGoodsVo.setGoodsSn(goodsItem.getGoodsSn());
//            orderGoodsVo.setProductId(goodsItem.getProductId());
//            orderGoodsVo.setGoodsName(goodsItem.getGoodsName());
//            orderGoodsVo.setListPicUrl(goodsItem.getListPicUrl());
//            orderGoodsVo.setMarketPrice(goodsItem.getMarketPrice());
//            orderGoodsVo.setRetailPrice(goodsItem.getRetailPrice());
//            orderGoodsVo.setNumber(goodsItem.getNumber());
//            orderGoodsVo.setGoodsSpecifitionNameValue(goodsItem.getGoodsSpecifitionNameValue());
//            orderGoodsVo.setGoodsSpecifitionIds(goodsItem.getGoodsSpecifitionIds());
//            orderGoodsData.add(orderGoodsVo);
//            OrderGoods orderGoods = new OrderGoods();
//            BeanUtils.mapping(orderGoodsVo, orderGoods);
//            orderGoodsMapper.insert(orderGoods);
//        }

        //清空已购买的商品
        cartMapper.deleteByCart(userId, 1, 1);
        resultObj.put("errno", 0);
        resultObj.put("errmsg", "订单提交成功");
        //
        Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
        orderInfoMap.put("orderInfo", orderInfo);
        //
        resultObj.put("data", orderInfoMap);
        // 优惠券标记已用
//        if (couponVo != null && couponVo.getCoupon_status() == 1) {
//            couponVo.setCoupon_status(2);
//            apiCouponMapper.updateUserCoupon(couponVo);
//        }

        return resultObj;
    }

    @Override
    public OrderVo queryObject(Integer id) {
        OrderVo orderVo = orderMapper.queryObject(id);

        return orderVo;
    }

    @Override
    public List<OrderVo> queryList(Map map) {
        List<OrderVo> list = orderMapper.queryList(map);
        return list;
    }

    @Override
    public Integer queryTotal(Map map) {
        return null;
    }

//    void voToEo(OrderVo orderVo, Order order){
//        if(orderVo.getActual_price() != null){
//            order.setActualPrice(orderVo.getActual_price());
//        }
//        if(orderVo.getAdd_time() != null){
//            order.setAddTime(orderVo.getAdd_time());
//        }
//        if(orderVo.getAddress() != null){
//            order.setAddress(orderVo.getAddress());
//        }
//        if(orderVo.getCallback_status()!=null){
//            order.setCallbackStatus(orderVo.getCallback_status());
//        }
//        if(orderVo.getCity()!=null){
//            order.setCity(orderVo.getCity());
//        }
//        if(orderVo.getConfirm_time() != null){
//            order.setConfirmTime(orderVo.getConfirm_time());
//        }
//        if(orderVo.getConsignee()!=null){
//            order.setConsignee(orderVo.getConsignee());
//        }
//        if(orderVo.getCountry()!=null){
//            order.setCountry(orderVo.getCountry());
//        }
//        if(orderVo.getDistrict()!=null){
//            order.setDistrict(orderVo.getDistrict());
//        }
//        if(orderVo.getFreight_price()!=null){
//            order.setFreightPrice(orderVo.getFreight_price());
//        }
//        if(orderVo.getGoods_price()!=null){
//            order.setGoodsPrice(orderVo.getGoods_price());
//        }
//        if(orderVo.getOrder_sn()!=null){
//            order.setOrderSn(orderVo.getOrder_sn());
//        }
//        if(orderVo.getUser_id()!=null){
//            order.setUserId(orderVo.getUser_id());
//        }
//        if(orderVo.getOrder_status()!=null){
//            order.setOrderStatus(orderVo.getOrder_status());
//        }
//        if(orderVo.getShipping_status()!=null){
//            order.setShippingStatus(orderVo.getShipping_status());
//        }
//        if(orderVo.getPay_status()!=null){
//            order.setPayStatus(orderVo.getPay_status());
//        }
//        if(orderVo.getConsignee()!=null){
//            order.setConsignee(orderVo.getConsignee());
//        }
//        if(orderVo.getProvince()!=null){
//            order.setProvince(orderVo.getProvince());
//        }
//        if(orderVo.getDistrict()!=null){
//            order.setDistrict(order.getDistrict());
//        }
//        if(orderVo.getMobile()!=null){
//            order.setMobile(orderVo.getMobile());
//        }
//        if(orderVo.getPostscript()!=null){
//            order.setPostscript(orderVo.getPostscript());
//        }
//        if(orderVo.getShipping_name()!=null){
//            order.setShippingName(orderVo.getShipping_name());
//        }
//        if(orderVo.getShipping_id()!=null){
//            order.setShippingId(orderVo.getShipping_id());
//        }
//        if(orderVo.getPay_name()!=null){
//            order.setShippingName(orderVo.getPay_name());
//        }
//        if(orderVo.getShipping_fee()!=null){
//            order.setShippingFee(orderVo.getShipping_fee());
//        }
//        if(orderVo.getActual_price()!=null){
//            order.setActualPrice(orderVo.getActual_price());
//        }
//        if(orderVo.getIntegral() != null){
//            order.setIntegral(orderVo.getIntegral());
//        }
//        if(orderVo.getIntegral_money() != null){
//            order.setIntegralMoney(orderVo.getIntegral_money());
//        }
//        if(orderVo.getOrder_price()!=null){
//            order.setOrderPrice(orderVo.getOrder_price());
//        }
//        if(orderVo.getPay_name()!=null){
//            order.setPayName(orderVo.getPay_name());
//        }
//        if(orderVo.getFreight_price()!=null){
//            order.setFreightPrice(orderVo.getFreight_price());
//        }
//        if(orderVo.getParent_id() != null){
//            order.setParentId(orderVo.getParent_id());
//        }
//        if(orderVo.getShipping_no() != null){
//            order.setShippingNo(orderVo.getShipping_no());
//        }
//        if(orderVo.getFull_cut_price() != null){
//            order.setFullCutPrice(orderVo.getFull_cut_price());
//        }
//        if(orderVo.getOrder_type() != null){
//            order.setOrderType(orderVo.getOrder_type());
//        }
//
//
//    }






}
