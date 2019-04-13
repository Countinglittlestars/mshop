package com.skymall.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiniu.util.StringUtils;
import com.skymall.annotation.LoginUser;
import com.skymall.constant.WcConstant;
import com.skymall.domain.Address;
import com.skymall.domain.Cart;
import com.skymall.exception.ApiRRException;
import com.skymall.service.IWcAddressService;
import com.skymall.service.IWcCartService;
import com.skymall.service.IWcProductService;
import com.skymall.utils.GuavaCacheUtil;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.AddressVo;
import com.skymall.vo.wechat.BuyGoodsVo;
import com.skymall.vo.wechat.CartVo;
import com.skymall.vo.wechat.ProductVo;
import com.skymall.web.dto.CartUpdateReqDto;
import com.skymall.web.dto.requestDto.CartCheckReqDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mshop/wc/cart")
public class WcCartController extends AbstractController{


    @Value("SHOP_CACHE_NAME")
    String shopCache;

    @Resource
    IWcCartService cartService;

    @Resource
    IWcAddressService addressService;

    @Resource
    IWcProductService productService;

    /**
     * 获取用户所有购物车内容
     * @return
     */
    @RequestMapping
    @PostMapping("/getCart")
    public Object getCart(){
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        Object obj  = cartService.getCart(userId);
        return obj;
    }

    /**
     * 获取购物车信息，所有对购物车的增删改操作，都要重新返回购物车的信息
     */
    @ApiOperation(value = "获取购物车信息")
    @PostMapping("/index")
    public Object index() {
        return Response.success(getCart());
    }


    /**
     * 添加商品到购物车
     *
     */
    @PostMapping(value = "/add")
    public Object add(){
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        JSONObject jsonParam = getJsonRequest();
        Object object = cartService.add(jsonParam, userId);

        return Response.success(object);
    }


    /**
     * 从购物车中删除
     * @return
     */
    @PostMapping(value = "/remove")
    public Object remove(){
        JSONObject jsonParam = getJsonRequest();
        cartService.removeCart(jsonParam);

        return Response.success();
    }


    @PostMapping(value = "/delete")
    public Object delete(){
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        JSONObject jsonObject = getJsonRequest();
        Object object = cartService.delete(jsonObject, userId);
        return Response.success(object);
    }


    @PostMapping("/update")
    public Object update(@RequestBody CartUpdateReqDto cartUpdateReqDto) {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        JSONObject jsonParam = (JSONObject) JSON.toJSON(cartUpdateReqDto);
        Object obj = cartService.updateIndex(jsonParam, userId);

        return Response.success(obj);
    }

    @ApiOperation(value = "是否选择商品")
    @PostMapping("/checked")
    public Object checked(@RequestBody CartCheckReqDto cartCheckReqDto) {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        JSONObject jsonParam  = (JSONObject) JSON.toJSON(cartCheckReqDto) ;
        cartService.checked(jsonParam, userId);
        return Response.success(cartService.getCart(userId));
    }

    @PostMapping("/goodscount")
    public Object goodsCount() {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        Integer count = cartService.count(new QueryWrapper<Cart>().lambda().eq(Cart::getUserId, userId));
        return Response.success(count);
    }




    @ApiOperation(value = "订单提交前的检验和填写相关订单信息")
    @PostMapping("checkout")
    public Object checkout(Integer couponId, @RequestParam(defaultValue = "cart") String type) {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        Map<String, Object> resultObj = new HashMap();
        //根据收货地址计算运费

        BigDecimal freightPrice = new BigDecimal(0.00);
        //默认收货地址
        //--------------------------------------------第一个接口
        List addressEntities = addressService.list(new QueryWrapper<Address>().lambda().eq(Address::getUserId, userId));

        if (null == addressEntities || addressEntities.size() == 0) {
            resultObj.put("checkedAddress", new Address());
        } else {
            resultObj.put("checkedAddress", addressEntities.get(0));
        }
        // * 获取要购买的商品和总价
        ArrayList checkedGoodsList = new ArrayList();
        BigDecimal goodsTotalPrice = null;
        if (type.equals("cart")) {
            Map<String, Object> cartData = (Map<String, Object>) this.getCart();

            for (CartVo cartEntity : (List<CartVo>) cartData.get("cartList")) {
                if (cartEntity.getChecked() == true) {
                    checkedGoodsList.add(cartEntity);
                }
            }
            goodsTotalPrice = (BigDecimal) ((HashMap) cartData.get("cartTotal")).get("checkedGoodsAmount");
        } else { // 是直接购买的
//            BuyGoodsVo goodsVO = (BuyGoodsVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "goods" + loginUser.getUserId() + "");
            BuyGoodsVo goodsVO = (BuyGoodsVo) GuavaCacheUtil.getKey(shopCache+userId);
            ProductVo productInfo = productService.queryObject(goodsVO.getProductId());
            //计算订单的费用
            //商品总价
            goodsTotalPrice = productInfo.getRetailPrice().multiply(new BigDecimal(goodsVO.getNumber()));

            CartVo cartVo = new CartVo();
            cartVo.setGoodsName(productInfo.getGoodsName());
            cartVo.setNumber(goodsVO.getNumber());
            cartVo.setRetailPrice(productInfo.getRetailPrice());
            cartVo.setListPicUrl(productInfo.getListPicUrl());
            checkedGoodsList.add(cartVo);
        }


        //获取可用的优惠券信息
//        BigDecimal couponPrice = new BigDecimal(0.00);
//        if (couponId != null && couponId != 0) {
//            CouponVo couponVo = apiCouponMapper.getUserCoupon(couponId);
//            if (couponVo != null) {
//                couponPrice = couponVo.getType_money();
//            }
//        }

        //订单的总价
        BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice);

        //
//        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额
        BigDecimal actualPrice = orderTotalPrice;
        resultObj.put("freightPrice", freightPrice);
//        resultObj.put("couponPrice", couponPrice);
        resultObj.put("checkedGoodsList", checkedGoodsList);
        resultObj.put("goodsTotalPrice", goodsTotalPrice);
        resultObj.put("orderTotalPrice", orderTotalPrice);
        resultObj.put("actualPrice", actualPrice);
        return Response.success(resultObj);
    }

    @PostMapping("/delete1")
    public Object delete1() {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();

        JSONObject jsonObject = getJsonRequest();
        String productIds = jsonObject.getString("productIds");

        if (StringUtils.isNullOrEmpty(productIds)) {
            return Response.error("删除出错");
        }
        String[] productIdsArray = productIds.split(",");

        cartService.deleteByUserAndProductIds(userId, productIdsArray);
//
        return Response.success(getCart());
    }



}
