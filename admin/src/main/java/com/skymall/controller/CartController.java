package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.CartMapper;
import com.skymall.domain.Cart;
import com.skymall.domain.Goods;
import com.skymall.service.impl.CartServiceImpl;
import com.skymall.service.impl.GoodServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author linchusen
 * @date 2019/3/14
 * 购物车管理
 */
@RestController
@RequestMapping("/admin/cart")
public class CartController {

    @Resource
    private CartServiceImpl cartService;
    @Resource
    private GoodServiceImpl goodService;

    /**
     * 新增购物车
     * @param cart
     * @return
     */
    @RequestMapping(value = "/addCart",method = RequestMethod.POST )
    public Object addCart(@RequestBody Cart cart){
        cartService.save(cart);
        return new CommonResult().success(cart.getId());
    }

    /**
     * 分页查询所有购物车
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/queryCart",method = RequestMethod.GET )
    public Object queryAllCartByPage(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                       @RequestParam (name = "size",defaultValue = "10") Integer size){
        Page<Cart> cartPage = new Page<>(page,size);
        IPage<Cart> data = cartService.queryByPage(cartPage);
        return new CommonResult().success(data);
    }


    /**
     * 根据用户Id查询购物车信息
     * @param page
     * @param size
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryByUserId/{userId}",method = RequestMethod.GET )
    public Object queryByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                  @RequestParam (name = "size",defaultValue = "10") Integer size,
                                  @PathVariable String userId){
        Page<Cart> cartPage = new Page<>(page,size);
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        IPage<Cart> data;
        data = cartService.pageByExample(cartPage,queryWrapper.eq("user_id",userId));
        return new CommonResult().success(data);
    }

    /**
     * 根据商品SN删除购物车
     * @param goodsSn
     * @return
     */
    @RequestMapping(value = "/removeByGoodsSn",method = RequestMethod.DELETE )
    public Object removeByGoodsId(@RequestParam String goodsSn){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        cartService.remove(queryWrapper.eq("goods_sn",goodsSn));
        return new CommonResult().success("删除成功");
    }

//    /**
//     * 清空无效商品
//     * @return
//     *
//     * NoSuchMethodError
//     */
//    @RequestMapping(value = "/removeUnSaleGoods",method = RequestMethod.DELETE )
//    public Object removeUnSaleGoods(){
//        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
//        QueryWrapper<Goods> goodsQueryWrapper = new QueryWrapper<>();
//        List<Cart> unableIds = cartService.list
//                (cartQueryWrapper.eq("goods_id",goodService.list
//                        (goodsQueryWrapper.eq("is_on_sale",0))));
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("unableId",unableIds);
//        cartService.removeByMap(map);
//        return new CommonResult().success("操作成功");
//    }

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    @RequestMapping(value = "/removeCart",method = RequestMethod.DELETE )
    public Object removeCart(@RequestParam Integer userId){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        cartService.remove(queryWrapper.eq("user_id",userId));
        return new CommonResult().success("操作成功");
    }
}
