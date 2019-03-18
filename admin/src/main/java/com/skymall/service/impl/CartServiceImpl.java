package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.CartMapper;
import com.skymall.domain.Cart;
import com.skymall.service.ICartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service("ICartServices")
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Resource
    private CartMapper cartMapper;


    @Override
    public IPage<Cart> queryByPage(Page<Cart> page) {
        return cartMapper.selectPage(page,null);
    }

    @Override
    public IPage<Cart> pageByCondition(Page<Cart> page, QueryWrapper<Cart> queryWrapper) {
        return cartMapper.selectPage(page,queryWrapper);
    }
}
