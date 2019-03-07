package com.skymall.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("nideshop_brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 品牌名称
     */
         private String name;

        /**
     * 图片
     */
         private String listPicUrl;

        /**
     * 描述
     */
         private String simpleDesc;

        /**
     * 图片
     */
         private String picUrl;

        /**
     * 排序
     */
         private Integer sortOrder;

        /**
     * 显示
     */
         private Boolean isShow;

    private BigDecimal floorPrice;

        /**
     * app显示图片
     */
         private String appListPicUrl;

        /**
     * 新品牌
     */
         private Boolean isNew;

        /**
     * 图片
     */
         private String newPicUrl;

        /**
     * 排序
     */
         private Integer newSortOrder;


}
