package com.skymall.bo;

import com.skymall.domain.SysPermissions;
import com.skymall.domain.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 * Created by macro on 2018/4/26.
 */
public class AdminUserDetails implements UserDetails {
    private SysUser umsAdmin;
    private List<SysPermissions> permissionList;
    public AdminUserDetails(SysUser umsAdmin, List<SysPermissions> permissionList) {
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(permissionList != null){
            return permissionList.stream()
                    .filter(permission -> permission.getValue()!=null)
                    .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                    .collect(Collectors.toList());
            //返回当前用户的权限
        }

        System.out.println("没有权限");
        return new ArrayList();
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
