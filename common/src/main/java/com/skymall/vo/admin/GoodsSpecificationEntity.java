package com.skymall.vo.admin;

import lombok.Data;

import java.io.Serializable;
@Data
public class GoodsSpecificationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //商品
    private Integer goodsId;
    //规范Id
    private Integer specificationId;
    //规范说明
    private String value;
    //规范图片
    private String picUrl;

    /**
     * 翻译用字段
     */
    //商品
    private String goodsName;
    //规范
    private String specificationName;

}
