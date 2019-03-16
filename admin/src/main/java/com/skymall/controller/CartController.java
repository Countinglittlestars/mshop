package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.CartMapper;
import com.skymall.domain.Cart;
import com.skymall.service.impl.CartServiceImpl;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linchusen
 * @date 2019/3/14
 * 购物车管理
 */
@RestController
public class CartController {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private CartServiceImpl cartServiceImpl;

    /**
     * 新增购物车
     * @param cart
     * @return
     */
    @RequestMapping(value = "/addCart",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response addCart(@RequestBody Cart cart){
        cartServiceImpl.save(cart);
        return Response.success(cart.getId());
    }

    /**
     * 分页查询所有购物车
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/queryCart",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryAllCartByPage(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                       @RequestParam (name = "size",defaultValue = "10") Integer size){
        Page<Cart> cartPage = new Page<>(page,size);
        IPage<Cart> data = cartMapper.selectPage(cartPage,null);
        return Response.success(data);
    }


    /**
     * 根据用户Id查询购物车信息
     * @param page
     * @param size
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryByUserId/{userId}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                  @RequestParam (name = "size",defaultValue = "10") Integer size,
                                  @PathVariable String userId){
        Page<Cart> cartPage = new Page<>(page,size);
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        IPage<Cart> data;
        data = cartMapper.selectPage(cartPage,queryWrapper.eq("user_id",userId));
        return Response.success(data);
    }

    /**
     * 根据商品SN删除购物车
     * @param goodsSn
     * @return
     */
    @RequestMapping(value = "/removeByGoodsSn",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeByGoodsId(@RequestParam String goodsSn){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        cartServiceImpl.remove(queryWrapper.eq("goods_sn",goodsSn));
        return Response.success();
    }

    /**
     * 清空无效商品
     * @return
     *
     * NoSuchMethodError
     */
    @RequestMapping(value = "/removeUnableGoods",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeUnableGoods(){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
//        int unableId = cartMapper.selectUnableGoods();
//        cartServiceImpl.remove(queryWrapper.eq("goods_id",unableId));
        return Response.success();
    }

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    @RequestMapping(value = "/removeCart",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeCart(@RequestParam Integer userId){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        cartServiceImpl.remove(queryWrapper.eq("user_id",userId));
        return Response.success();
    }
}