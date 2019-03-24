package com.skymall.controller;

import com.skymall.dao.GoodsAttributeMapper;
import com.skymall.domain.GoodsAttribute;
import com.skymall.service.impl.GoodsAttributeServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linchusen
 *
 * 商品参数值管理
 */
@Api(description = "商品参数值管理")
@RestController
public class GoodsAttributeController {
    @Resource
    private GoodsAttributeServiceImpl goodsAttributeService;

    /**
     * 添加商品参数值
     * @param goodsAttribute
     * @return
     */
    @ApiOperation(value = "添加商品参数值")
    @RequestMapping(value = "/addGoodsAttr",method = RequestMethod.POST )
    public Object addGoodsAttribute(@RequestBody GoodsAttribute goodsAttribute){
        goodsAttributeService.save(goodsAttribute);
        return new CommonResult().success(goodsAttribute.getId());
    }

}
