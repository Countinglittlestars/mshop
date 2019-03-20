package com.skymall.dto;

import lombok.Data;

@Data
public class GoodQueryDto {
    private String name;
    private String goodsSn;
    private Integer categoryId;
    private Integer brandId;
    private Boolean isOnSale;
    private Boolean isNew;
    private Boolean isHot;


}
