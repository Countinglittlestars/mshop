package com.skymall.dao;

import com.skymall.domain.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.vo.wechat.CartVo;
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
public interface CartMapper extends BaseMapper<Cart> {
//    @Select("SELECT goods_id from nideshop_cart where (select goods_id from nideshop_goods where is_on_sale = 0)")
    public List<Cart> selectUnSaleGoods();

    public List<CartVo> queryList(Map map);

}
