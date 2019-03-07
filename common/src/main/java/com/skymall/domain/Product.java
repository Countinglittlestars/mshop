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
@TableName("nideshop_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 商品Id
     */
         private Integer goodsId;

        /**
     * 商品规格ids
     */
         private String goodsSpecificationIds;

        /**
     * 商品序列号
     */
         private String goodsSn;

        /**
     * 商品编码
     */
         private Integer goodsNumber;

        /**
     * 零售价格
     */
         private BigDecimal retailPrice;

        /**
     * 价格
     */
         private BigDecimal marketPrice;


}
