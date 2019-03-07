package com.skymall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 任务id
     */
         @TableId(value = "job_id", type = IdType.AUTO)
    private Long jobId;

        /**
     * spring bean名称
     */
         private String beanName;

        /**
     * 方法名
     */
         private String methodName;

        /**
     * 参数
     */
         private String params;

        /**
     * cron表达式
     */
         private String cronExpression;

        /**
     * 任务状态
     */
         private Integer status;

        /**
     * 备注
     */
         private String remark;

        /**
     * 创建时间
     */
         private LocalDateTime createTime;


}
