package com.skymall.web.dto;

import lombok.Data;

@Data
public class CartUpdateReqDto {
    Integer productId;
    Integer goodsId;
    Integer number;
    Integer id;

}
