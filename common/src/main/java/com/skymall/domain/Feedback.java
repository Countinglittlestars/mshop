package com.skymall.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("nideshop_feedback")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
         @TableId(value = "msg_id", type = IdType.AUTO)
    private Integer msgId;

        /**
     * 会员Id
     */
         private Integer userId;

        /**
     * 会员会员名称
     */
         private String userName;

        /**
     * 手机
     */
         private String mobile;

        /**
     * 反馈类型
     */
         @TableField("feed_Type")
    private Boolean feedType;

        /**
     * 详细内容
     */
         private String content;

        /**
     * 状态
     */
         private Boolean status;

        /**
     * 反馈时间
     */
         private LocalDateTime addTime;


}
