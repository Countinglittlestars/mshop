package com.skymall.dto;

import com.skymall.domain.Attribute;
import com.skymall.domain.GoodsAttribute;
import com.skymall.domain.GoodsGallery;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class GoodAddDto {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 商品序列号
     */
    private String goodsSn;
    /**
     * 名称
     */
    private String name;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 库存数量
     */
    private Integer goodsNumber;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 简要介绍
     */
    private String goodsBrief;

    /**
     */
    private List<Attribute> attributeList;

    /**
     * 参数类型id
     */
    private Integer attributeCategoryId;
    /**
     * 商品详情，里面主要存放照片
     */
    private String goodsDesc;

    /**
     * 是否上架
     */
    private Boolean isOnSale;

    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 是否是新品
     */
    private Boolean isNew;
    /**
     * 参数类型
     */
    private Integer attributeCategory;

    /**
     * 专柜价格,市场价
     */
    private BigDecimal counterPrice;

    /**
     * 附加价格 暂时不用填写
     */
    private BigDecimal extraPrice;

    /**
     * 商品单位,单位写一下
     */
    private String goodsUnit;

    /**
     * 零售价格
     */
    private BigDecimal retailPrice;

    /**
     * 商品主图
     */
    private String primaryPicUrl;

    /**
     * 商品列表图
     */
    private String listPicUrl;

    private Boolean isHot;

    private List<String> pics;


}
