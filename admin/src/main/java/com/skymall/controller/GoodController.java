package com.skymall.controller;

import com.skymall.domain.Goods;
import com.skymall.dto.GoodAddDto;
import com.skymall.dto.GoodQueryDto;
import com.skymall.dto.GoodUpdateInfoDto;
import com.skymall.service.IGoodService;
import com.skymall.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "商品管理")
@RestController
@RequestMapping(value = "/admin/good")
public class GoodController {

    @Resource
    IGoodService goodService;

    /**
     *
     * @param goodDto
     * @return
     */
    @ApiOperation(value = "新增商品")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object addGood(@RequestBody GoodAddDto goodDto){

        goodService.addGood(goodDto);
        return new CommonResult().success();
    }

    @ApiOperation(value = "更新商品")
        @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    public Object updateInfo(@PathVariable(value = "id") Integer id){
        GoodUpdateInfoDto goodsUpdateInfoDto =  goodService.querySelect(id);
        return new CommonResult().success(goodsUpdateInfoDto);
    }


    @ApiOperation(value = "分页查询商品")
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public Object queryByPage(GoodQueryDto goodQueryDto,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        return new CommonResult().success(goodService.queryByPage(goodQueryDto, pageNum, pageSize));
    }

    @ApiOperation(value = "更新商品IsHot")
    @RequestMapping(value = "/updateIsHot", method = RequestMethod.GET)
    public Object updateIsHot(@RequestParam(value = "isHot") Boolean isHot,
                              @RequestParam(value = "id") Integer id){
        Goods goods = goodService.getById(id);
        goods.setIsHot(isHot);
        goodService.updateById(goods);
        return new CommonResult().success();

    }

    @ApiOperation(value = "更新商品IsNew")
    @RequestMapping(value = "/updateIsNew", method = RequestMethod.GET)
    public Object updateIsNew(@RequestParam(value = "isNew") Boolean isNew,
                              @RequestParam(value = "id") Integer id){
        Goods goods = goodService.getById(id);
        goods.setIsNew(isNew);
        goodService.updateById(goods);
        return new CommonResult().success();
    }

    @ApiOperation(value = "上架商品")
    @RequestMapping(value = "/updateOnSale", method = RequestMethod.GET)
    public Object updateOnSale(@RequestParam(value = "isOnSale") Boolean isOnSale,
                               @RequestParam(value = "id") Integer id
                               ){
        Goods goods = goodService.getById(id);
        goods.setIsOnSale(isOnSale);
        goodService.updateById(goods);
        return new CommonResult().success();
    }


}