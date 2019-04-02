package com.skymall.web.dto.requestDto;

import lombok.Data;

@Data
public class CartCheckReqDto {
    String productIds;
    Boolean isChecked;


}
