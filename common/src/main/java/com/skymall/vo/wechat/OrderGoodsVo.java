package com.skymall.vo.wechat;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderGoodsVo {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //订单Id
    private Integer orderId;
    //商品id
    private Integer goodsId;
    //商品名称
    private String goodsName;
    //商品序列号
    private String goodsSn;
    //产品Id
    private Integer productId;
    //商品数量
    private Integer number;
    //市场价
    private BigDecimal marketPrice;
    //零售价格
    private BigDecimal retailPrice;
    //商品规格详情
    private String goodsSpecifitionNameValue;
    //虚拟商品
    private Integer isReal;
    //商品规格Ids
    private String goodsSpecifitionIds;
    //图片链接
    private String listPicUrl;
}
