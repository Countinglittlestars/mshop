package com.skymall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Cart;
import com.skymall.dto.CartQueryDto;
import com.skymall.vo.admin.CartEntitiy;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */

public interface ICartService extends IService<Cart> {
    public IPage<CartEntitiy> queryByPage(CartQueryDto cartQueryDto, Page<CartEntitiy> page);
    public IPage<Cart> pageByExample(Page<Cart> page, QueryWrapper<Cart> queryWrapper);
//    public List<Cart> selectUnSaleGoods();
}
