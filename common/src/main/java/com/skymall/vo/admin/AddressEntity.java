package com.skymall.vo.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class AddressEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 会员Id
     */
    private Integer userId;
    /**
     * 会员名称
     */
    private String memberName;

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

    private Integer isDefault;

    Integer page;

    Integer size;


}
