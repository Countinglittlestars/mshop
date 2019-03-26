package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.CartMapper;
import com.skymall.domain.Cart;
import com.skymall.dto.CartQueryDto;
import com.skymall.service.ICartService;
import com.skymall.vo.admin.CartEntitiy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public IPage<CartEntitiy> queryByPage(CartQueryDto cartQueryDto, Page<CartEntitiy> page) {
        IPage result = cartMapper.queryPage(page, cartQueryDto);
        return result;
    }

    @Override
    public IPage<Cart> pageByExample(Page<Cart> page, QueryWrapper<Cart> queryWrapper) {
        return cartMapper.selectPage(page,queryWrapper);
    }
//
//    @Override
//    public List<Cart> selectUnSaleGoods() {
//
//        return cartMapper.selectUnSaleGoods();
//    }


}
