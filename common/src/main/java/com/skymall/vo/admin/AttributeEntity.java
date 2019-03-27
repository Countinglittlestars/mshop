package com.skymall.vo.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class AttributeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    private Integer id;

    /**
     * 属性分类名
     */
    @TableField("name")
    private String attributeCategoryName;

    /**
     * 属性名
     */
    @TableField("name")
    private String attributeName;

    @TableField("sort_order")
    private Integer sortOrder;

}
