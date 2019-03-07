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
@TableName("nideshop_order_goods")
public class OrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 订单Id
     */
         private Integer orderId;

        /**
     * 商品id
     */
         private Integer goodsId;

        /**
     * 商品名称
     */
         private String goodsName;

        /**
     * 商品序列号
     */
         private String goodsSn;

        /**
     * 产品Id
     */
         private Integer productId;

        /**
     * 商品数量
     */
         private Integer number;

        /**
     * 市场价
     */
         private BigDecimal marketPrice;

        /**
     * 零售价格
     */
         private BigDecimal retailPrice;

        /**
     * 商品规格详情
     */
         private String goodsSpecifitionNameValue;

        /**
     * 虚拟商品
     */
         private Boolean isReal;

        /**
     * 商品规格Ids
     */
         private String goodsSpecifitionIds;

        /**
     * 图片链接
     */
         private String listPicUrl;


}
