package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.CartMapper;
import com.skymall.domain.Cart;
import com.skymall.domain.Goods;
import com.skymall.dto.CartQueryDto;
import com.skymall.service.ICartService;
import com.skymall.service.IGoodService;
import com.skymall.service.impl.CartServiceImpl;
import com.skymall.service.impl.GoodServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import com.skymall.vo.admin.CartEntitiy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author linchusen
 * @date 2019/3/14
 * 购物车管理
 */
@Api(description = "购物车管理")
@RestController
@RequestMapping("/admin/cart")
public class CartController {

    @Resource
    private ICartService cartService;
    @Resource
    private IGoodService goodService;

    /**
     * 新增购物车
     */
    @ApiOperation(value = "新增购物车")
    @RequestMapping(value = "/add",method = RequestMethod.POST )
    public Object addCart(@RequestBody Cart cart){
        cartService.save(cart);
        return new CommonResult().success(cart.getId());
    }

    /**
     * 分页查询所有购物车
     */
    @ApiOperation(value = "分页查询所有购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页信息数", required = false, dataType = "Integer")
    })
    @RequestMapping(value = "/queryByPage",method = RequestMethod.GET )
    public Object queryAllCartByPage(CartQueryDto cartQueryDto,
                                        @RequestParam (name = "pageNum",defaultValue = "1") Integer page,
                                       @RequestParam (name = "pageSize",defaultValue = "5") Integer size){
        Page<CartEntitiy> cartPage = new Page<>(page,size);
        IPage<CartEntitiy> data = cartService.queryByPage(cartQueryDto, cartPage);
        return new CommonResult().success(data);
    }


    /**
     * 根据用户Id查询购物车信息
     */
    @ApiOperation(value = "根据用户Id查询购物车信息")
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
     */
    @ApiOperation(value = "根据商品SN删除购物车")
    @ApiImplicitParam(paramType="delete", name = "goodsSn", value = "商品sn", required = true, dataType = "Integer")
    @RequestMapping(value = "/removeByGoodsSn",method = RequestMethod.DELETE )
    public Object removeByGoodsId(@RequestParam String goodsSn){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        cartService.remove(queryWrapper.eq("goods_sn",goodsSn));
        return new CommonResult().success();
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
     */
    @ApiOperation(value = "清空购物车")
    @ApiImplicitParam(paramType="delete", name = "userId", value = "用户Id", required = true, dataType = "Integer")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE )
    public Object removeCart(@RequestParam Integer userId){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        cartService.remove(queryWrapper.eq("user_id",userId));
        return new CommonResult().success();
    }
}
