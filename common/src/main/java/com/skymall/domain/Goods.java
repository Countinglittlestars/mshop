package com.skymall.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("nideshop_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer categoryId;

    private String goodsSn;

    private String name;

    private Integer brandId;

    private Integer goodsNumber;

    private String keywords;

    private String goodsBrief;

    private String goodsDesc;

    private Boolean isOnSale;

    private LocalDateTime addTime;

    private Integer sortOrder;

    private Boolean isDelete;

    private Integer attributeCategory;

        /**
     * 专柜价格
     */
         private BigDecimal counterPrice;

        /**
     * 附加价格
     */
         private BigDecimal extraPrice;

    private Boolean isNew;

        /**
     * 商品单位
     */
         private String goodsUnit;

        /**
     * 商品主图
     */
         private String primaryPicUrl;

        /**
     * 商品列表图
     */
         private String listPicUrl;

        /**
     * 零售价格
     */
         private BigDecimal retailPrice;

        /**
     * 销售量
     */
         private Integer sellVolume;

        /**
     * 主sku　product_id
     */
         private Integer primaryProductId;

        /**
     * 单位价格，单价
     */
         private BigDecimal unitPrice;

    private String promotionDesc;

    private String promotionTag;

        /**
     * APP专享价
     */
         private BigDecimal appExclusivePrice;

        /**
     * 是否是APP专属
     */
         private Boolean isAppExclusive;

    private Boolean isLimited;

    private Boolean isHot;

    private BigDecimal marketPrice;

        /**
     * 创建人ID
     */
         private Long createUserId;

        /**
     * 修改人ID
     */
         private Long updateUserId;

        /**
     * 修改时间
     */
         private LocalDateTime updateTime;

    private Long createUserDeptId;


}
