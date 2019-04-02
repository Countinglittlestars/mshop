package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.OrderGoodsMapper;
import com.skymall.dao.OrderMapper;
import com.skymall.domain.OrderGoods;
import com.skymall.service.IWcOrderGoodsService;
import com.skymall.vo.wechat.OrderGoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class WcOrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoods> implements IWcOrderGoodsService {

    @Resource
    OrderGoodsMapper orderGoodsMapper;

    @Override
    public List<OrderGoodsVo> queryList(Map map) {

        return orderGoodsMapper.queryList(map);

    }
}
