package com.skymall.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.dto.CartQueryDto;
import com.skymall.vo.admin.CartEntitiy;
import com.skymall.vo.wechat.CartVo;
import org.apache.ibatis.annotations.Param;

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
     List<Cart> selectUnSaleGoods();

     List<CartVo> queryList(Map map);

     IPage<CartEntitiy> queryPage(Page page, CartQueryDto dto);

     void deleteByUserAndProductIds(@Param("user_id") Integer user_id, @Param("productIds") String[] productIds);

     void updateCheck(@Param("productIds") String[] productIds,
                      @Param("isChecked") Boolean isChecked, @Param("userId") Integer userId);

     void deleteByCart(@Param("user_id") Integer user_id, @Param("session_id") Integer session_id, @Param("checked") Integer checked);




}
