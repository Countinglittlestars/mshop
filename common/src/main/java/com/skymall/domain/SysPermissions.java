package com.skymall.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer pid;

    private String name;

    private String value;

    /**
     * 权限类型: 0-目录; 1-菜单;2-按钮
     */
    private Integer type;

    private String uri;

    private Integer status;

    private Date createTime;

    private Date updateTime;



}
