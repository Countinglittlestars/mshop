package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.GoodsSpecification;
import com.skymall.dto.GoodSpecificationQueryDto;
import com.skymall.dto.GoodsSpecQueryDto;
import com.skymall.service.IGoodsSpecificationService;
import com.skymall.vo.CommonResult;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/admin/goodsSpecification")
public class GoodsSpecificationController {

    @Resource
    IGoodsSpecificationService goodsSpecificationService;

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public Object queryAll(GoodSpecificationQueryDto goodSpecificationQueryDto){
        return new CommonResult().success(goodsSpecificationService.queryList(goodSpecificationQueryDto));
    }

    /**
     * 新增商品规格
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addGoodsSpec(@RequestBody GoodsSpecification goodsSpecification){
        goodsSpecificationService.save(goodsSpecification);
        return new CommonResult().success();
    }



    /**
     * 分页查询所有商品规格
     */
    @RequestMapping(value = "/queryAllByPage",method = RequestMethod.GET)
    public Object queryGoodsSpecByPage(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size){
        Page page1 = new Page(page,size);
        return new CommonResult().success(goodsSpecificationService.queryAll(page1));
    }

    /**
     * 根据id查询商品规格
     */
    @RequestMapping(value = "/queryById/{id}",method = RequestMethod.GET)
    public Object queryById(@PathVariable Integer id){
        return new CommonResult().success(goodsSpecificationService.queryById(id));
    }

    /**
     * 根据goodsSN，goodsName，specName分页查询
     */
    @RequestMapping(value = "/queryByExample",method = RequestMethod.GET)
    public Object queryByExample(GoodsSpecQueryDto goodsSpecQueryDto,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size){
        Page page1 = new Page(page,size);
        return new CommonResult().success
                (goodsSpecificationService.queryByExample(page1,goodsSpecQueryDto));
    }

    /**
     * 根据id删除商品规格
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteGoodsSpec(@PathVariable Integer id){
        return new CommonResult().success
                (new QueryWrapper<GoodsSpecification>().lambda().eq(GoodsSpecification::getId,id));
    }




}
