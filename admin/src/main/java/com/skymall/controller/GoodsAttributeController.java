package com.skymall.controller;

import com.skymall.dao.GoodsAttributeMapper;
import com.skymall.domain.GoodsAttribute;
import com.skymall.service.impl.GoodsAttributeServiceImpl;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @author linchusen
 *
 * 商品参数值管理
 */
public class GoodsAttributeController {
    @Resource
    private GoodsAttributeMapper goodsAttributeMapper;
    @Resource
    private GoodsAttributeServiceImpl goodsAttributeServiceImpl;

    /**
     * 添加商品参数值
     * @param goodsAttribute
     * @return
     */
    @RequestMapping(value = "/addGoodsAttr",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response addGoodsAttribute(@RequestBody GoodsAttribute goodsAttribute){
        goodsAttributeServiceImpl.save(goodsAttribute);
        return Response.success(goodsAttribute.getId());
    }

    
}
