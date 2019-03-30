package com.skymall.vo.admin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linchusen
 */
@Data
public class GoodSpecificationEntity implements Serializable {
    private static final long serialVersionUID = 1L;



    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品序列码
     */
    private String goodsSN;

    /**
     * 规格id
     */
    private Integer specId;

    /**
     * 规格名
     */
    private String specName;

    /**
     * 规格id
     */
    private Integer id;

    /**
     * 规格说明
     */
    private String goodsSpecValue;
}
