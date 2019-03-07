package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.ChannelMapper;
import com.skymall.domain.Channel;
import com.skymall.service.IWcChannelService;
import com.skymall.vo.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WcChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements IWcChannelService {

    @Resource
    ChannelMapper channelMapper;

    public Response selectAll(){

        QueryWrapper entityWrapper = new QueryWrapper();
        entityWrapper.orderByAsc("sort_order");
        List<Channel> list = channelMapper.selectList(entityWrapper);


        return Response.success(list);
    }
}
