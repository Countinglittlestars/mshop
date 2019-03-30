package com.skymall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsSpecQueryDto {
    @ApiModelProperty(value = "商品SN", required = false)
    private String goodsSN;
    @ApiModelProperty(value = "商品名称", required = false)
    private String goodsName;
    @ApiModelProperty(value = "规格说明", required = false)
    private String specName;
}
