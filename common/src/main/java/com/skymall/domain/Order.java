package com.skymall.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nideshop_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderSn;

    private Integer userId;

    private Integer orderStatus;

    private Integer shippingStatus;

    private Integer payStatus;

    private String consignee;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String mobile;

    private String postscript;

    private Integer shippingId;

    private String shippingName;

    private String payId;

    private String payName;

    private BigDecimal shippingFee;

        /**
     * 实际需要支付的金额
     */
         private BigDecimal actualPrice;

    private Integer integral;

    private BigDecimal integralMoney;

        /**
     * 订单总价
     */
         private BigDecimal orderPrice;

        /**
     * 商品总价
     */
         private BigDecimal goodsPrice;

    private Date addTime;

    private Date confirmTime;

    private Date payTime;

        /**
     * 配送费用
     */
         private Integer freightPrice;

        /**
     * 使用的优惠券id
     */
         private Integer couponId;

    private Integer parentId;

    private BigDecimal couponPrice;

    private Integer callbackStatus;

    private String shippingNo;

    private BigDecimal fullCutPrice;

    private String orderType;


}
