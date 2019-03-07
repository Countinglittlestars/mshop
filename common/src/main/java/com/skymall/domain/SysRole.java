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
 * 角色
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

        /**
     * 角色名称
     */
         private String roleName;

        /**
     * 备注
     */
         private String remark;

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
