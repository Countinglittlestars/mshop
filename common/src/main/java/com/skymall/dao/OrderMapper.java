package com.skymall.dao;

import com.skymall.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.vo.wechat.OrderVo;

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
public interface OrderMapper extends BaseMapper<Order> {

    OrderVo queryObject(Object id);

    List<OrderVo> queryList(Map map);

    Integer quertTotal();
}
