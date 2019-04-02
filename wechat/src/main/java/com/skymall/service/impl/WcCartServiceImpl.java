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
import io.swagger.annotations.Api;
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
            if (null != cartItem.getChecked() && true == cartItem.getChecked()) {
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
        if (null == productInfo || productInfo.getGoodsNumber() < number) {
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
//            if (null != productInfo.getGoods_specification_ids() && productInfo.getGoods_specification_ids().length() > 0) {
                if (null != productInfo.getGoodsSpecificationIds() && productInfo.getGoodsSpecificationIds().length() > 0) {
                Map specificationParam = new HashMap();
                String[] idsArray = getSpecificationIdsArray(productInfo.getGoodsSpecificationIds());
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
            cartInfo.setGoodsSn(productInfo.getGoodsSn());
            cartInfo.setGoodsName(goodsInfo.getName());
            cartInfo.setListPicUrl(goodsInfo.getListPicUrl());
            cartInfo.setNumber(number);
            cartInfo.setSessionId("1");
            cartInfo.setUserId(userId);
            cartInfo.setRetailPrice(productInfo.getRetailPrice());
            cartInfo.setMarketPrice(productInfo.getMarketPrice());
            if (null != goodsSepcifitionValue) {
                cartInfo.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValue, ";"));
            }
            cartInfo.setGoodsSpecifitionIds(productInfo.getGoodsSpecificationIds());
            cartInfo.setChecked(true);
            Cart cart = new Cart();
            BeanUtils.mapping(cartInfo, cart);
            cartMapper.insert(cart);
        }else{
//如果已经存在购物车中，则数量增加
            if (productInfo.getGoodsNumber() < (number + cartInfo.getNumber())) {
                return new ApiRRException("库存不足", 400);
            }
            cartInfo.setNumber(cartInfo.getNumber() + number);
            Cart cart = new Cart();
            BeanUtils.mapping(cartInfo, cart);
            cartMapper.updateById(cart);
        }

//        return Response.success(getCart(userId));
        return getCart(userId);
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

    @Override
    public Object updateIndex(JSONObject jsonParam, Integer userId) {
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Integer number = jsonParam.getInteger("number");
        Integer id = jsonParam.getInteger("id");
        //取得规格的信息,判断规格库存
        ProductVo productInfo = productService.queryObject(productId);
        if (null == productInfo || productInfo.getGoodsNumber() < number) {
            throw new ApiRRException("库存不足", 400);
        }
        //判断是否已经存在product_id购物车商品
//        CartVo cartInfo = cartMapper.queryObject(id);
        Cart cartInfo = cartMapper.selectById(id);
        //只是更新number
        if (cartInfo.getProductId().equals(productId)) {
            cartInfo.setNumber(number);
            //cartInfo信息转到Cart上
            cartMapper.updateById(cartInfo);
            //返回getCart信息
            return getCart(userId);
//            return toResponsSuccess(getCart(loginUser));
        }

        Map cartParam = new HashMap();
        cartParam.put("goods_id", goodsId);
        cartParam.put("product_id", productId);
        List<CartVo> cartInfoList = cartMapper.queryList(cartParam);
        CartVo newcartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        if (null == newcartInfo) {
            //添加操作
            //添加规格名和值
            String[] goodsSepcifitionValue = null;
            if (null != productInfo.getGoodsSpecificationIds()) {
                Map specificationParam = new HashMap();
                specificationParam.put("ids", productInfo.getGoodsSpecificationIds());
                specificationParam.put("goodsId", goodsId);
                List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
                goodsSepcifitionValue = new String[specificationEntities.size()];
                for (int i = 0; i < specificationEntities.size(); i++) {
                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
                }
            }
            cartInfo.setProductId(productId);
            cartInfo.setGoodsSn(productInfo.getGoodsSn());
            cartInfo.setNumber(number);
            cartInfo.setRetailPrice(productInfo.getRetailPrice());
            cartInfo.setMarketPrice(productInfo.getRetailPrice());
            if (null != goodsSepcifitionValue) {
                cartInfo.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValue, ";"));
            }
            cartInfo.setGoodsSpecifitionIds(productInfo.getGoodsSpecificationIds());
            Cart cart = new Cart();
            BeanUtils.mapping(cartInfo, cart);
            cartMapper.updateById(cart);
        } else {
            //合并购物车已有的product信息，删除已有的数据
            Integer newNumber = number + newcartInfo.getNumber();
            if (null == productInfo || productInfo.getGoodsNumber() < newNumber) {
                throw new ApiRRException("库存不足", 400);
            }
            cartMapper.deleteById(newcartInfo.getId());
            //添加规格名和值
            String[] goodsSepcifitionValue = null;
            if (null != productInfo.getGoodsSpecificationIds()) {
                Map specificationParam = new HashMap();
                specificationParam.put("ids", productInfo.getGoodsSpecificationIds());
                specificationParam.put("goodsId", goodsId);
                List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
                goodsSepcifitionValue = new String[specificationEntities.size()];
                for (int i = 0; i < specificationEntities.size(); i++) {
                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
                }
            }
            cartInfo.setProductId(productId);
            cartInfo.setGoodsSn(productInfo.getGoodsSn());
            cartInfo.setNumber(number);
            cartInfo.setRetailPrice(productInfo.getRetailPrice());
            cartInfo.setMarketPrice(productInfo.getRetailPrice());
            if (null != goodsSepcifitionValue) {
                cartInfo.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValue, ";"));
            }
            cartInfo.setGoodsSpecifitionIds(productInfo.getGoodsSpecificationIds());
            Cart cart = new Cart();
            BeanUtils.mapping(cartInfo, cart);
            cartMapper.updateById(cart);
        }


//        return toResponsSuccess(getCart(loginUser));
        return getCart(userId);


    }

    @Override
    public Object delete(JSONObject jsonObject, Integer userId) {
        String productIds = jsonObject.getString("productIds");
        if (StringUtils.isNullOrEmpty(productIds)) {
            throw new ApiRRException("删除出错", 500);
        }
        String[] productIdsArray = productIds.split(",");
        cartMapper.deleteByUserAndProductIds(userId, productIdsArray);
        return getCart(userId);
    }

    @Override
    public Object checked(JSONObject jsonObject, Integer userId) {

        String productIds = jsonObject.getString("productIds");
        Boolean isChecked = jsonObject.getBoolean("isChecked");
        if (StringUtils.isNullOrEmpty(productIds)) {
            throw new ApiRRException("删除出错", 500);
        }
        String[] productIdArray = productIds.split(",");
        try {
            cartMapper.updateCheck(productIdArray, isChecked, userId);
        }catch (Exception e){
            throw new ApiRRException(e.getMessage());
        }
        return null;
    }

}
