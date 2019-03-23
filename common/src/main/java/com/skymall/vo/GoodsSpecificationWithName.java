package com.skymall.vo;

import lombok.Data;

@Data
public class GoodsSpecificationWithName {
    private Integer id;

    private Integer goodsId;

    private Integer specificationId;

    private String value;

    private String picUrl;

    private String name;

}
