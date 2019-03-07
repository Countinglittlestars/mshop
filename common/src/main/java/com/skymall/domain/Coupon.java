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
@TableName("nideshop_coupon")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal typeMoney;

    private Integer sendType;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private LocalDateTime sendStartDate;

    private LocalDateTime sendEndDate;

    private LocalDateTime useStartDate;

    private LocalDateTime useEndDate;

    private BigDecimal minGoodsAmount;

        /**
     * 转发次数
     */
         private Integer minTransmitNum;


}
