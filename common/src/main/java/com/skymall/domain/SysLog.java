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
 * 系统日志
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

        /**
     * 用户名
     */
         private String username;

        /**
     * 用户操作
     */
         private String operation;

        /**
     * 请求方法
     */
         private String method;

        /**
     * 请求参数
     */
         private String params;

        /**
     * IP地址
     */
         private String ip;

        /**
     * 创建时间
     */
         private LocalDateTime createDate;


}
