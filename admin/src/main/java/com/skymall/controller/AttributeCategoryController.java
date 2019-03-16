package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skymall.dao.AttributeCategoryMapper;
import com.skymall.domain.AttributeCategory;
import com.skymall.service.impl.AttributeCategoryServiceImpl;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author linchusen
 * @date 2019/3/15
 * 商品参数类型管理
 */

@RestController
public class AttributeCategoryController {
    @Resource
    private AttributeCategoryMapper attributeCategoryMapper;
    @Resource
    private AttributeCategoryServiceImpl attributeCategoryServiceImpl;

    /**
     * 新增商品参数类型
     * @param attributeCategory
     * @return
     */
    @RequestMapping(value = "/addAttrCate",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response addAttributeCategory(@RequestBody AttributeCategory attributeCategory){
        attributeCategoryServiceImpl.save(attributeCategory);
        return Response.success(attributeCategory.getId());
    }

    /**
     * 根据id修改商品参数类型
     * @param attributeCategory
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateAttrCate/{id}",method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public Response updateAttributeCategory(@RequestBody AttributeCategory attributeCategory,
                                            @PathVariable Integer id){
        UpdateWrapper<AttributeCategory> updateWrapper = new UpdateWrapper<>();
        attributeCategoryMapper.update(attributeCategory,updateWrapper.eq("id",id));
        return Response.success();
    }

    /**
     * 查询所有商品参数类型
     * @return
     */
    @RequestMapping(value = "/queryAttrCate",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryAttributeCategory(){
        List<AttributeCategory> list = attributeCategoryServiceImpl.list(null);
        HashMap<String,Object> map = new HashMap<>();
        map.put("allAttributeCategory",list);
        return Response.success(map);
    }

    /**
     * 根据id删除商品参数类型
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteAttrCate",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeAttributeCategory(@RequestParam Integer id){
        QueryWrapper<AttributeCategory> queryWrapper = new QueryWrapper<>();
        attributeCategoryServiceImpl.remove(queryWrapper.eq("id",id));
        return Response.success();
    }
}