package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.SysUserMapper;
import com.skymall.domain.SysPermissions;
import com.skymall.domain.SysUser;
import com.skymall.dto.UmsAdminParam;
import com.skymall.service.ISysUserService;
import com.skymall.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenHead = "Bearer";
    @Resource
    private SysUserMapper sysUserMapper;
//    @Resource
//    private SysPermissionsMapper sysPermissionsMapper;


    /**
     * 登陆并返回token
     * @param userName
     * @param password
     * @return
     */
    public String login(String userName, String password){
        String token = null;
        //密码需要客户端加密后传递
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     *  注册账号返回用户信息
     * @param user
     * @return
     */
    public SysUser register(UmsAdminParam user){
        SysUser umsAdmin = new SysUser();
        BeanUtils.copyProperties(user, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SysUser::getUsername, user.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        List<SysUser> umsAdminList = sysUserMapper.selectList(queryWrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
//        String md5Password = .encodePassword(umsAdmin.getPassword(), null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(user.getPassword());
        umsAdmin.setPassword(password);
        sysUserMapper.insert(umsAdmin);
        return umsAdmin;
    }

    /**
     * 根据用户名返回用户信息
     * @param userName
     * @return
     */
    public SysUser getUserByUserName(String userName) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userName", userName);
        SysUser user = sysUserMapper.selectOne(queryWrapper);

        return user;
    }

    /**
     * 根据用户id返回权限信息
     * @param userId
     * @return
     */
    public List<SysPermissions> getPermissionList(Integer userId){
//        List<SysPermissions> list = sysPermissionsMapper.getPermissionList(userId);
//        return list;
        return  null;
    }
}
