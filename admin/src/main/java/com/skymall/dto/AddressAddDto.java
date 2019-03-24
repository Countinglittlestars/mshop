package com.skymall.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class AddressAddDto {

    private static final long serialVersionUID = 1L;

    /**
     * 会员ID
     */
    @ApiModelProperty("会员ID")
    private Integer userId;

    /**
     * 收货人姓名
     */
    @ApiModelProperty("收货人姓名")
    private String userName;

    /**
     * 手机
     */
    @ApiModelProperty("手机")
    private String telNumber;

    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    @TableField("postal_Code")
    private String postalCode;

    /**
     * 收货地址国家码
     */
    @ApiModelProperty("收货地址国家码")
    @TableField("national_Code")
    private String nationalCode;

    /**
     * 省
     */
    @ApiModelProperty("省")
    @TableField("province_Name")
    private String provinceName;

    /**
     * 市
     */
    @ApiModelProperty("市")
    @TableField("city_Name")
    private String cityName;

    /**
     * 区
     */
    @ApiModelProperty("区")
    @TableField("county_Name")
    private String countyName;

    /**
     * 详细收货地址信息
     */
    @ApiModelProperty("详细收货地址信息")
    @TableField("detail_Info")
    private String detailInfo;

    /**
     * 默认地址
     */
    @ApiModelProperty("是否默认")
    @TableField("is_default")
    private Integer isDefault;

}
