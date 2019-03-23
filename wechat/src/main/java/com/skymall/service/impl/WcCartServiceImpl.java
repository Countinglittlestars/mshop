package com.skymall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.JsonObject;
import com.qiniu.util.StringUtils;
import com.skymall.dao.CartMapper;
import com.skymall.domain.Cart;
import com.skymall.domain.Goods;
import com.skymall.exception.ApiRRException;
import com.skymall.service.IWcCartService;
import com.skymall.service.IWcGoodService;
import com.skymall.service.IWcGoodSpecificationService;
import com.skymall.service.IWcProductService;
import com.skymall.utils.BeanUtils;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.CartVo;
import com.skymall.vo.wechat.GoodsSpecificationVo;
import com.skymall.vo.wechat.ProductVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WcCartServiceImpl extends ServiceImpl<CartMapper, Cart> implements IWcCartService  {

    @Resource
    CartMapper cartMapper;

    @Resource
    IWcGoodService goodService;

    @Resource
    IWcProductService productService;

    @Resource
    IWcGoodSpecificationService goodsSpecificationService;


    @Override
    public Object getCart(Integer userId) {
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        Map param = new HashMap();
        param.put("user_id", userId);
        List<CartVo> cartList = cartMapper.queryList(param);
        //获取购物车统计信息
        Integer goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0.00);
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (CartVo cartItem : cartList) {
            goodsCount += cartItem.getNumber();
            goodsAmount = goodsAmount.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            if (null != cartItem.getChecked() && 1 == cartItem.getChecked()) {
                checkedGoodsCount += cartItem.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            }
        }
        resultObj.put("cartList", cartList);
        //
        Map<String, Object> cartTotal = new HashMap();
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);
        //
        resultObj.put("cartTotal", cartTotal);
        return resultObj;
    }

    @Override
    public Object add(JSONObject jsonObject, Integer userId) {
        JSONObject jsonParam = jsonObject;
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Integer number = jsonParam.getInteger("number");
        //1. 判断商品是否可以购买
        List goods = goodService.list(new QueryWrapper<Goods>().lambda().eq(Goods::getId, goodsId)
                .eq(Goods::getIsDelete, 0).eq(Goods::getIsOnSale, true));
        if(goods.isEmpty()){
            throw new ApiRRException("商品已经下架" , 400);
        }
        Goods goodsInfo = (Goods) goods.get(0);
        //2. 取得规格的信息,判断规格库存
        ProductVo productInfo = productService.queryObject(productId);
        if (null == productInfo || productInfo.getGoods_number() < number) {
            throw new ApiRRException("库存不足" , 400);
        }
        //3. 判断购物车中是否存在此规格商品
        Map cartParam = new HashMap();
        cartParam.put("goods_id", goodsId);
        cartParam.put("product_id", productId);
        cartParam.put("user_id", userId);
        //---------------------------->这里可能会报错
        List<CartVo> cartInfoList = queryList(cartParam);
        CartVo cartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        //4. 如果已经存在购物车中，则数量增加
        if (null == cartInfo) {
            //添加操作
            //添加规格名和值
            String[] goodsSepcifitionValue = null;
            if (null != productInfo.getGoods_specification_ids() && productInfo.getGoods_specification_ids().length() > 0) {
                Map specificationParam = new HashMap();
                String[] idsArray = getSpecificationIdsArray(productInfo.getGoods_specification_ids());
                specificationParam.put("ids", idsArray);
                specificationParam.put("goods_id", goodsId);
                List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
                goodsSepcifitionValue = new String[specificationEntities.size()];
                for (int i = 0; i < specificationEntities.size(); i++) {
                    GoodsSpecificationVo goodsSpecificationVo = specificationEntities.get(i);
                    goodsSepcifitionValue[i] = goodsSpecificationVo.getValue();
                }
            }
            cartInfo = new CartVo();

            cartInfo.setGoodsId(goodsId);
            cartInfo.setProductId(productId);
            cartInfo.setGoodsSn(productInfo.getGoods_sn());
            cartInfo.setGoodsName(goodsInfo.getName());
            cartInfo.setListPicUrl(goodsInfo.getListPicUrl());
            cartInfo.setNumber(number);
            cartInfo.setSessionId("1");
            cartInfo.setUserId(userId);
            cartInfo.setRetailPrice(productInfo.getRetail_price());
            cartInfo.setMarketPrice(productInfo.getMarket_price());
            if (null != goodsSepcifitionValue) {
                cartInfo.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValue, ";"));
            }
            cartInfo.setGoodsSpecifitionIds(productInfo.getGoods_specification_ids());
            cartInfo.setChecked(1);
            Cart cart = new Cart();
            BeanUtils.mapping(cartInfo, cart);
            cartMapper.insert(cart);
        }

        return Response.success(getCart(userId));
    }


    private String[] getSpecificationIdsArray(String ids) {
        String[] idsArray = null;
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] tempArray = ids.split("_");
            if (null != tempArray && tempArray.length > 0) {
                idsArray = tempArray;
            }
        }
        return idsArray;
    }

    @Override
    public Object removeCart(JSONObject jsonObject) {
        return null;
    }

    @Override
    public List<CartVo> queryList(Map<String, Object> map) {
        return cartMapper.queryList(map);
    }

}
