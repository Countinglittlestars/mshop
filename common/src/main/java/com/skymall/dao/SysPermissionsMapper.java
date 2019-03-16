package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.domain.SysPermissions;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-15
 */
public interface SysPermissionsMapper extends BaseMapper<SysPermissions> {
    public List<SysPermissions> getPermissionList(Integer userId);
}
