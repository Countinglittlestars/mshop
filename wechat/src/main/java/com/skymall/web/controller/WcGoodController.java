package com.skymall.web.controller;

import com.skymall.annotation.IgnoreAuth;
import com.skymall.domain.Goods;
import com.skymall.domain.GoodsGallery;
import com.skymall.service.IWcGoodService;
import com.skymall.service.IWcGoodsGalleryService;
import com.skymall.utils.ApiPageUtils;
import com.skymall.vo.Response;

import com.skymall.web.dto.requestDto.GoodQueryReqDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/mshop/wc/goods")
public class WcGoodController {
    @Resource
    IWcGoodsGalleryService goodsGalleryService;


    @Resource
    IWcGoodService goodService;

    @IgnoreAuth
    @RequestMapping(value = "queryNew", method = RequestMethod.POST)
    public Response queryNew(){
        List<Goods> newGoodsList = goodService.selectNewGoods();
        return Response.success(newGoodsList);
    }

    @IgnoreAuth
    @RequestMapping(value = "queryHot", method = RequestMethod.POST)
    public Response queryHot(){
        List<Goods> hotGoodsList = goodService.selectHotGoods();
        return Response.success(hotGoodsList);
    }

    @IgnoreAuth
    @RequestMapping(value = "queryByCatagory", method = RequestMethod.POST)
    public Response queryByCatagory(@RequestBody GoodQueryReqDto goodQueryReqDto){
        //判断是否有指定排序的对象，如果没指定的话，用默认的字段来指定
        //找出所有的正常显示商品的categoryId
        //如果能找到对应的商品Goods，则把所有的Goods的categoryId存储在Lists<Integer>中
        //查找二级分类Lists<Integer>的Category对应的parentId到parentIds
        //根据parentIds得到所有的一级分类parentCategory
        //filterCategory添加所有的一级分类
        //查询所有categroyReqDto.categoryId 的子分类的id，组成categoryIds
        //上面内容纯属废话，忽略无视
        ApiPageUtils data = goodService.queryAll(goodQueryReqDto);
        return Response.success(data);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Response detail(@RequestParam(value = "id")Integer id){
        Map map = goodService.detail(id);
        return Response.success(map);
    }

}
