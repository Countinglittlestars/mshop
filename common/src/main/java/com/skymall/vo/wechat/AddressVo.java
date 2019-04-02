package com.skymall.vo.wechat;

import lombok.Data;

@Data
public class AddressVo {
    //
    private Long id;
    //会员ID
    private Long userId;
    //收货人姓名
    private String userName;
    //手机
    private String telNumber;
    //邮编
    private String postalCode;
    //收货地址国家码
    private String nationalCode;
    //省
    private String provinceName;
    //市
    private String cityName;
    //区
    private String countyName;
    //详细收货地址信息
    private String detailInfo;

    //默认
    private Integer isDefault = 0;

    private String full_region;
}
