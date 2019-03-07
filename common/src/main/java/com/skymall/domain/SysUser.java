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
 * 系统用户
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

        /**
     * 用户名
     */
         private String username;

        /**
     * 密码
     */
         private String password;

        /**
     * 邮箱
     */
         private String email;

        /**
     * 手机号
     */
         private String mobile;

        /**
     * 状态  0：禁用   1：正常
     */
         private Integer status;

        /**
     * 创建者ID
     */
         private Long createUserId;

        /**
     * 创建时间
     */
         private LocalDateTime createTime;

        /**
     * 部门ID
     */
         private Long deptId;


}
