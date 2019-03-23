package com.skymall.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class AddressAddDto {

    private static final long serialVersionUID = 1L;

    /**
     * 会员ID
     */
    private Integer userId;

    /**
     * 收货人姓名
     */
    private String userName;

    /**
     * 手机
     */
    private String telNumber;

    /**
     * 邮编
     */
    @TableField("postal_Code")
    private String postalCode;

    /**
     * 收货地址国家码
     */
    @TableField("national_Code")
    private String nationalCode;

    /**
     * 省
     */
    @TableField("province_Name")
    private String provinceName;

    /**
     * 市
     */
    @TableField("city_Name")
    private String cityName;

    /**
     * 区
     */
    @TableField("county_Name")
    private String countyName;

    /**
     * 详细收货地址信息
     */
    @TableField("detail_Info")
    private String detailInfo;

    /**
     * 默认地址
     */
    @TableField("is_default")
    private Integer isDefault;

}
