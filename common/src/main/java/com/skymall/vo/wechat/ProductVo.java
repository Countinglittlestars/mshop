package com.skymall.vo.wechat;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVo {
    //主键
    private Integer id;
    //商品Id
    private Integer goodsId;
    //产品Id
    private Integer productId;
    //商品规格ids
    private String goodsSpecificationIds;
    //商品序列号
    private String goodsSn;
    //商品库存
    private Integer goodsNumber;
    //零售价格
    private BigDecimal marketPrice;
    //时长价
    private BigDecimal retailPrice;
    //商品名称
    private String goodsName;
    //商品图片
    private String listPicUrl;
}
