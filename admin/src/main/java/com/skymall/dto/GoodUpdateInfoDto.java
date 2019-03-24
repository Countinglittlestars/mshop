package com.skymall.dto;

import com.skymall.domain.Attribute;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class GoodUpdateInfoDto {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Integer categoryId;
    /**
     *  父分类
     */
    @ApiModelProperty("父分类")
    private Integer parentCategoryId;

    /**
     * 商品序列号
     */
    @ApiModelProperty("商品序列号")
    private String goodsSn;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    private Integer brandId;

    /**
     *  品牌名称
     */
    @ApiModelProperty("品牌名称")
    private String brandName;

    /**
     * 库存数量
     */
    @ApiModelProperty("库存数量")
    private Integer goodsNumber;

    /**
     * 关键字
     */
    @ApiModelProperty("关键字")
    private String keywords;

    /**
     * 简要介绍
     */
    @ApiModelProperty("简要介绍")
    private String goodsBrief;

    /**
     */
    private List<Attribute> attributeList;

    /**
     * 参数类型id
     */
    @ApiModelProperty("参数类型id")
    private Integer attributeCategoryId;
    /**
     * 商品详情，里面主要存放照片
     */
    @ApiModelProperty("商品详情，里面主要存放照片")
    private String goodsDesc;

    /**
     * 是否上架
     */
    @ApiModelProperty("是否上架")
    private Boolean isOnSale;

    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    private Date addTime;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sortOrder;

    /**
     * 是否是新品
     */
    @ApiModelProperty("是否是新品")
    private Boolean isNew;
    /**
     * 参数类型
     */
    @ApiModelProperty("参数类型")
    private Integer attributeCategory;

    /**
     * 专柜价格,市场价
     */
    @ApiModelProperty("专柜价格,市场价")
    private BigDecimal counterPrice;

    /**
     * 附加价格 暂时不用填写
     */
    @ApiModelProperty("附加价格 暂时不用填写")
    private BigDecimal extraPrice;

    /**
     * 商品单位,单位写一下
     */
    @ApiModelProperty("商品单位,单位写一下")
    private String goodsUnit;

    /**
     * 零售价格
     */
    @ApiModelProperty("零售价格")
    private BigDecimal retailPrice;

    /**
     * 商品主图
     */
    @ApiModelProperty("商品主图")
    private String primaryPicUrl;

    /**
     * 商品列表图
     */
    @ApiModelProperty("商品列表图")
    private String listPicUrl;

    private Boolean isHot;

    private List<String> pics;


}
