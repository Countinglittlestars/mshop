package com.skymall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class GoodSpecificationQueryDto {

    @ApiModelProperty(value = "商品名称",required = false)
    private String goodsName;
    @ApiModelProperty(value = "商品id", required = false)
    private Integer goodsId;
    @ApiModelProperty(value = "规格id", required = false)
    private Integer specificationId;


}
