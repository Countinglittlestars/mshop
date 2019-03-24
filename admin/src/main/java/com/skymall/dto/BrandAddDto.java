package com.skymall.dto;

import lombok.Data;

@Data
public class BrandAddDto {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌描述
     */
    private String simpleDesc;

    /**
     * 展示
     */
    private Boolean isShow;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 新品牌
     */
    private Boolean isNew;

    /**
     * 新品牌排序
     */
    private Integer newSortOrder;


}
