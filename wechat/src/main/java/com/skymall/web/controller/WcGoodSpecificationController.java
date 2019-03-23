package com.skymall.web.controller;

import com.skymall.annotation.IgnoreAuth;
import com.skymall.domain.GoodsSpecification;
import com.skymall.service.IWcGoodSpecificationService;
import com.skymall.vo.GoodsSpecificationWithName;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/mshop/wc/goodSpecification")
public class WcGoodSpecificationController {

    @Resource
    IWcGoodSpecificationService wcGoodSpecificationService;

    @IgnoreAuth
    @RequestMapping(value = "/getSpecificationWithName/{goodId}", method = RequestMethod.GET)
    public Response getSpecificationWithName(@PathVariable(value = "goodId") Integer goodId){
        List<GoodsSpecificationWithName> goodsSpecificationList = wcGoodSpecificationService.getSpecificationWithName(goodId);
        return Response.success(goodsSpecificationList);
    }



}
