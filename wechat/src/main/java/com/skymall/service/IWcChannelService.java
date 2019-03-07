package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Channel;
import com.skymall.vo.Response;

public interface IWcChannelService extends IService<Channel> {
    public Response selectAll();
}
