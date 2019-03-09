package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.UserMapper;
import com.skymall.domain.User;
import com.skymall.service.IWcUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class WcUserServiceImpl extends ServiceImpl<UserMapper, User> implements IWcUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getByOpenId(String openId) {
        User user = userMapper.selectOne((Wrapper<User>) new QueryWrapper()
                .eq("weixin_openid", openId));
        return user;
    }

    @Override
    public Integer update(User user) {
        Integer id = userMapper.update(user,
                new QueryWrapper<User>().lambda().eq(User::getId, user.getId()));
        return id;
    }
}
