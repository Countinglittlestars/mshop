package com.skymall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddressQueryDto {
    @ApiModelProperty(value="id" ,required=false)
    Integer id;
    @ApiModelProperty(value="userId" ,required=false)
    Integer userId;
    @ApiModelProperty(value="userName" ,required=false)
    String userName;
}
