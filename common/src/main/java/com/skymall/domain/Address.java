package com.skymall.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nideshop_address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    private Integer isDefault;


}
