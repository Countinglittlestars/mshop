package com.skymall.controller;

import com.skymall.domain.Goods;
import com.skymall.dto.GoodAddDto;
import com.skymall.dto.GoodQueryDto;
import com.skymall.dto.GoodUpdateInfoDto;
import com.skymall.service.IGoodService;
import com.skymall.vo.CommonResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object addGood(@RequestBody GoodAddDto goodDto){

        goodService.addGood(goodDto);
        return new CommonResult().success();
    }

        @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    public Object updateInfo(@PathVariable(value = "id") Integer id){
        GoodUpdateInfoDto goodsUpdateInfoDto =  goodService.querySelect(id);
        return new CommonResult().success(goodsUpdateInfoDto);
    }


    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public Object queryByPage(GoodQueryDto goodQueryDto,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        return new CommonResult().success(goodService.queryByPage(goodQueryDto, pageNum, pageSize));
    }

    @RequestMapping(value = "/updateIsHot", method = RequestMethod.GET)
    public Object updateIsHot(@RequestParam(value = "isHot") Boolean isHot,
                              @RequestParam(value = "id") Integer id){
        Goods goods = goodService.getById(id);
        goods.setIsHot(isHot);
        goodService.updateById(goods);
        return new CommonResult().success();

    }

    @RequestMapping(value = "/updateIsNew", method = RequestMethod.GET)
    public Object updateIsNew(@RequestParam(value = "isNew") Boolean isNew,
                              @RequestParam(value = "id") Integer id){
        Goods goods = goodService.getById(id);
        goods.setIsNew(isNew);
        goodService.updateById(goods);
        return new CommonResult().success();
    }
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