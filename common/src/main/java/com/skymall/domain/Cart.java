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
@TableName("nideshop_cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 会员Id
     */
         private Integer userId;

        /**
     * sessionId
     */
         private String sessionId;

        /**
     * 商品Id
     */
         private Integer goodsId;

        /**
     * 商品序列号
     */
         private String goodsSn;

        /**
     * 产品Id
     */
         private Integer productId;

        /**
     * 产品名称
     */
         private String goodsName;

        /**
     * 市场价
     */
         private BigDecimal marketPrice;

        /**
     * 零售价格
     */
         private BigDecimal retailPrice;

        /**
     * 数量
     */
         private Integer number;

        /**
     * 规格属性组成的字符串，用来显示用
     */
         private String goodsSpecifitionNameValue;

        /**
     * product表对应的goods_specifition_ids
     */
         private String goodsSpecifitionIds;

    private Boolean checked;

        /**
     * 商品图片
     */
         private String listPicUrl;


}
