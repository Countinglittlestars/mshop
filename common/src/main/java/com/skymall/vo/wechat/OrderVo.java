package com.skymall.vo.wechat;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderVo {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //订单序列号
    private String orderSn;
    //会员Id
    private Integer userId;
    /*
    订单状态
    1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
    2xx 表示订单支付状态　201订单已付款，等待发货
    3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
    4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货
    */
    private Integer orderStatus;
    //发货状态 商品配送情况;0未发货,1已发货,2已收货,4退货
    private Integer shippingStatus;
    //付款状态 支付状态;0未付款;1付款中;2已付款;4退款
    private Integer payStatus;
    //收货人
    private String consignee;
    //国家
    private String country;
    //省
    private String province;
    //地市
    private String city;
    //区县
    private String district;
    //收货地址
    private String address;
    //联系电话
    private String mobile;
    //补充说明
    private String postscript;
    //快递公司Id
    private Integer shippingId;
    //快递公司code
    private String shippingCode;
    //快递公司名称
    private String shippingName;
    //快递号
    private String shippingNo;
    //付款
    private String payId;
    //
    private String payName;
    //快递费用
    private BigDecimal shippingFee;
    //实际需要支付的金额
    private BigDecimal actualPrice;
    // 积分
    private Integer integral;
    // 积分抵扣金额
    private BigDecimal integralMoney;
    //订单总价
    private BigDecimal orderPrice;
    //商品总价
    private BigDecimal goodsPrice;
    //新增时间
    private Date addTime;
    //确认时间
    private Date confirmTime;
    //付款时间
    private Date payTime;
    //配送费用
    private Integer freightPrice;
    //使用的优惠券id
    private Integer couponId;
    //
    private Integer parentId;
    //优惠价格
    private BigDecimal couponPrice;
    //
    private Integer callbackStatus;
    //
    private Integer goodsCount; //订单的商品
    private String orderStatusText;//订单状态的处理
    private Map handleOption; //可操作的选项
    private BigDecimal fullCutPrice; //订单满减
    private String fullRegion;//区县
    private String orderType; // 订单状态

    public String getFullRegion() {
        //    return full_region;
        if (StringUtils.isNotEmpty(this.fullRegion)){
            return fullRegion;
        } else{
            StringBuffer strBuff = new StringBuffer();
            if (StringUtils.isNotEmpty(this.country)){
                strBuff.append(this.country).append(" ");
            }
            if(StringUtils.isNotEmpty(this.province)){
                strBuff.append(this.province).append(" ");
            }
            if (StringUtils.isNotEmpty(this.city)){
                strBuff.append(this.city).append(" ");
            }
            if (StringUtils.isNotEmpty(this.district)){
                strBuff.append(this.district).append(" ");
            }
            this.fullRegion = strBuff.toString();
            return this.fullRegion;
        }
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String order_type) {
        this.orderType = order_type;
    }

    public void setFullRegion(String full_region) {
        this.fullRegion = full_region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String order_sn) {
        this.orderSn = order_sn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer order_status) {
        this.orderStatus = order_status;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shipping_status) {
        this.shippingStatus = shipping_status;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer pay_status) {
        this.payStatus = pay_status;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shipping_id) {
        this.shippingId = shipping_id;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shipping_name) {
        this.shippingName = shipping_name;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String pay_id) {
        this.payId = pay_id;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String pay_name) {
        this.payName = pay_name;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shipping_fee) {
        this.shippingFee = shipping_fee;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actual_price) {
        this.actualPrice = actual_price;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(BigDecimal integral_money) {
        this.integralMoney = integral_money;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal order_price) {
        this.orderPrice = order_price;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goods_price) {
        this.goodsPrice = goods_price;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date add_time) {
        this.addTime = add_time;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirm_time) {
        this.confirmTime = confirm_time;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date pay_time) {
        this.payTime = pay_time;
    }

    public Integer getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(Integer freight_price) {
        this.freightPrice = freight_price;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer coupon_id) {
        this.couponId = coupon_id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parent_id) {
        this.parentId = parent_id;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal coupon_price) {
        this.couponPrice = coupon_price;
    }

    public Integer getCallbackStatus() {
        return callbackStatus;
    }

    public void setCallbackStatus(Integer callback_status) {
        this.callbackStatus = callback_status;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getOrderStatusText() {
        if (null != orderStatus && StringUtils.isEmpty(orderStatusText)) {
            orderStatusText = "未付款";
            switch (orderStatus) {
                case 0:
                    orderStatusText = "未付款";
                    break;
                case 201:
                    orderStatusText = "等待发货";
                    break;
                case 300:
                    orderStatusText = "待收货";
                    break;
                case 301:
                    orderStatusText = "已完成";
                    break;
                case 200:
                    orderStatusText = "已付款";
                    break;
                case 101:
                    orderStatusText = "已取消";
                    break;
                case 401:
                    orderStatusText = "已取消";
                    break;
                case 402:
                    orderStatusText = "已退货";
                    break;
            }
        }
        return orderStatusText;
    }

    public void setOrderStatusText(String order_status_text) {
        this.orderStatusText = order_status_text;
    }

    //
    public Map getHandleOption() {
        handleOption = new HashMap();
        handleOption.put("cancel", false);//取消操作
        handleOption.put("delete", false);//删除操作
        handleOption.put("pay", false);//支付操作
        handleOption.put("comment", false);//评论操作
        handleOption.put("delivery", false);//确认收货操作
        handleOption.put("confirm", false);//完成订单操作
        handleOption.put("return", false); //退换货操作
        handleOption.put("buy", false); //再次购买

        //订单流程：　下单成功－》支付订单－》发货－》收货－》评论
        //订单相关状态字段设计，采用单个字段表示全部的订单状态
        //1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
        //2xx 表示订单支付状态　201订单已付款，等待发货
        //3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
        //4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货

        //如果订单已经取消或是已完成，则可删除和再次购买
        if (orderStatus == 101) {
//            handleOption.put("delete", true);
            handleOption.put("buy", true);
        }

        //如果订单没有被取消，且没有支付，则可支付，可取消
        if (orderStatus == 0) {
            handleOption.put("cancel", true);
            handleOption.put("pay", true);
        }

        //如果订单已付款，没有发货，则可退款操作
        if (orderStatus == 201) {
            handleOption.put("cancel", true);
        }

        //如果订单已经发货，没有收货，则可收货操作和退款、退货操作
        if (orderStatus == 300) {
//            handleOption.put("cancel", true);
            handleOption.put("confirm", true);
//            handleOption.put("return", true);
        }

        //如果订单已经支付，且已经收货，则可完成交易、评论和再次购买
        if (orderStatus == 301) {
            handleOption.put("comment", true);
            handleOption.put("buy", true);
        }
        return handleOption;
    }

    public void setHandleOption(Map handleOption) {
        this.handleOption = handleOption;
    }

    public BigDecimal getFullCutPrice() {
        return fullCutPrice;
    }

    public void setFullCutPrice(BigDecimal full_cut_price) {
        this.fullCutPrice = full_cut_price;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shipping_code) {
        this.shippingCode = shipping_code;
    }

    public String getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(String shipping_no) {
        this.shippingNo = shipping_no;
    }
}
