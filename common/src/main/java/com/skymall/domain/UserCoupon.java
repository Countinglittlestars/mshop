package com.skymall.domain;

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
@TableName("nideshop_user_coupon")
public class UserCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer couponId;

    private String couponNumber;

    private Integer userId;

    private LocalDateTime usedTime;

    private LocalDateTime addTime;

    private Integer orderId;

        /**
     * 来源key
     */
         private String sourceKey;

        /**
     * 发券人
     */
         private Integer referrer;

        /**
     * 状态 1. 可用 2. 已用 3. 过期
     */
         private Integer couponStatus;


}
