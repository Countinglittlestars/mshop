package com.skymall.vo.wechat;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVo {
    //主键
    private Integer id;
    //商品Id
    private Integer goods_id;
    //产品Id
    private Integer product_id;
    //商品规格ids
    private String goods_specification_ids;
    //商品序列号
    private String goods_sn;
    //商品库存
    private Integer goods_number;
    //零售价格
    private BigDecimal market_price;
    //时长价
    private BigDecimal retail_price;
    //商品名称
    private String goods_name;
    //商品图片
    private String list_pic_url;
}
