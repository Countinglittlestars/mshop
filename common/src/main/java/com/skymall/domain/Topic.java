package com.skymall.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("nideshop_topic")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
         private Integer id;

        /**
     * 活动主题
     */
         private String title;

        /**
     * 活动内容
     */
         private String content;

        /**
     * 化名
     */
         private String avatar;

        /**
     * 活动条例图片
     */
         private String itemPicUrl;

        /**
     * 子标题
     */
         private String subtitle;

        /**
     * 活动类别
     */
         private Integer topicCategoryId;

        /**
     * 活动价格
     */
         private BigDecimal priceInfo;

    private String readCount;

        /**
     * 场景图片链接
     */
         private String scenePicUrl;

        /**
     * 活动模板Id
     */
         private Integer topicTemplateId;

        /**
     * 活动标签Id
     */
         private Integer topicTagId;


}
