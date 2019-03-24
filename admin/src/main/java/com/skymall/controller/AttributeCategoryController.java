package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.skymall.domain.AttributeCategory;

import com.skymall.service.impl.AttributeCategoryServiceImpl;
import com.skymall.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

/**
 * @author linchusen
 * @date 2019/3/15
 * 商品参数类型管理
 */

@Api(description = "商品参数类型管理")
@RestController
@RequestMapping(value = "/admin/attributeCategory")
public class AttributeCategoryController {

    @Resource
    private AttributeCategoryServiceImpl attributeCategoryService;

    /**
     * 新增商品参数类型
     */
    @ApiOperation(value = "新增商品参数")
    @RequestMapping(value = "/add",method = RequestMethod.POST )
    public Object addAttributeCategory(@RequestBody AttributeCategory attributeCategory){
        attributeCategoryService.save(attributeCategory);
        return new CommonResult().success(attributeCategory.getId());
    }

    /**
     * 根据id修改商品参数类型
     */
    @ApiOperation(value = "根据id修改商品参数类型")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT )
    public Object updateAttributeCategory(@RequestBody AttributeCategory attributeCategory,
                                            @PathVariable Integer id){
        UpdateWrapper<AttributeCategory> updateWrapper = new UpdateWrapper<>();
        attributeCategoryService.update(attributeCategory,updateWrapper.eq("id",id));
        return new CommonResult().success();
    }

    /**
     * 查询所有商品参数类型
     */
    @ApiOperation(value = "查询所有商品参数类型")
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Object queryAttributeCategory(){
        List<AttributeCategory> list = attributeCategoryService.list(null);
        return new CommonResult().success(list);

    }




    /**
     * 根据id删除商品参数类型
     */
    @ApiOperation(value = "根据id删除商品参数类型")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE )
    public Object removeAttributeCategory(@RequestParam Integer id){
        QueryWrapper<AttributeCategory> queryWrapper = new QueryWrapper<>();
        attributeCategoryService.remove(queryWrapper.eq("id",id));
        return new CommonResult().success();
    }
}
