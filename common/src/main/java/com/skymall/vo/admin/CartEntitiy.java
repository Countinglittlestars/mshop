package com.skymall.vo.admin;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartEntitiy {
    //用户名称，商品名，商品序列号，商品主图，记录时间，是否上架
    String id;
    String goodsName;
    String goodsSn;
    String listPicUrl;
    String isOnSale;
    BigDecimal marketPrice;
    BigDecimal retailPrice;

}
