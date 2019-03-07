package com.skymall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

        /**
     * 上级部门ID，一级部门为0
     */
         private Long parentId;

        /**
     * 部门名称
     */
         private String name;

        /**
     * 排序
     */
         private Integer orderNum;

        /**
     * 是否删除  -1：已删除  0：正常
     */
         private Integer delFlag;


}
