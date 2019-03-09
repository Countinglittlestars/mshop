package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.User;

public interface IWcUserService extends IService<User> {

    User getByOpenId(String openId);

    Integer update(User user);
}
