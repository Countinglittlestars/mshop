package com.skymall.controller;

import com.skymall.dto.GoodSpecificationQueryDto;
import com.skymall.service.IGoodsSpecificationService;
import com.skymall.vo.CommonResult;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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






}
