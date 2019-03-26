package com.skymall.dto;

import lombok.Data;

@Data
public class CartQueryDto {
    private String userName;
    private Integer userId;
    private String goodsName;
}
