package com.skymall.dao;

import com.skymall.domain.OrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.vo.wechat.OrderGoodsVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {
    List<OrderGoodsVo> queryList(Map map);


}
