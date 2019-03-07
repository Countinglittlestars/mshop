package com.skymall.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 热闹关键词表
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nideshop_keywords")
public class Keywords implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 关键字
     */
         private String keyword;

        /**
     * 热销
     */
         private Boolean isHot;

        /**
     * 默认
     */
         private Boolean isDefault;

        /**
     * 显示
     */
         private Boolean isShow;

        /**
     * 排序
     */
         private Integer sortOrder;

        /**
     * 关键词的跳转链接
     */
         private String schemeUrl;

        /**
     * 类型
     */
         private Integer type;


}
