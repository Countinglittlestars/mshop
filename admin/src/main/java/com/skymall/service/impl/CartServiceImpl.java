package com.skymall.service.impl;

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


//    @Override
//    public int selectUnableGoods() {
//        return cartMapper.selectUnableGoods();
//    }
}
