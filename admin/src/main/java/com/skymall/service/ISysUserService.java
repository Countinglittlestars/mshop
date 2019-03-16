package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.SysPermissions;
import com.skymall.domain.SysUser;
import com.skymall.dto.UmsAdminParam;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface ISysUserService extends IService<SysUser> {
    public String login(String userName, String password);

    public SysUser register(UmsAdminParam user);

    public SysUser getUserByUserName(String userName);

    public List<SysPermissions> getPermissionList(Integer userId);

}
