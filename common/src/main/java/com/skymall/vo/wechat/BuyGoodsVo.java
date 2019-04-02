package com.skymall.vo.wechat;

import lombok.Data;

@Data
public class BuyGoodsVo {
    private static final long serialVersionUID = 1L;

    private Integer goodsId;
    private Integer productId;
    private Integer number;
    private String name;
}
