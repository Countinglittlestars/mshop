package com.skymall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductQueryDto {
    @ApiModelProperty(value="商品id, 例如1181000或者1166008" ,required=false)
    Integer goodsId;
    @ApiModelProperty(value="商品姓名 例如 魔兽世界" ,required=false)
    String goodsName;

}
