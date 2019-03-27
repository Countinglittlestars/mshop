package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skymall.dao.AttributeMapper;
import com.skymall.domain.Attribute;
import com.skymall.dto.AttributeQueryDto;
import com.skymall.service.impl.AttributeServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import com.skymall.vo.admin.AttributeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author linchusen
 * @date 2019/03/16
 *
 * 商品参数管理
 */

@Api(description = "商品参数管理")
@RestController
@RequestMapping("/admin/attribute")
public class AttributeController {
    @Resource
    private AttributeServiceImpl attributeService;

    /**
     * 新增商品参数
     */
    @ApiOperation(value = "新增商品参数")
    @RequestMapping(value = "/add",method = RequestMethod.POST )
    @ApiImplicitParam(name = "id", value = "参数详细实体attribute", paramType = "Attribute" )
    public Object addAttribute(@RequestBody Attribute attribute){
        attributeService.save(attribute);
        return new CommonResult().success(attribute.getId());
    }

    /**
     * 查询所有商品参数
     */
    @ApiOperation(value = "查询所有商品参数")
    @RequestMapping(value = "/query",method = RequestMethod.GET )
    public Object queryAttribute(){
        List<Attribute> list = attributeService.list(null);
        return new CommonResult().success(list);
    }

    /**
     * 根据属性种类名或属性名分页查询属性id，属性种类名，属性名
     */
    @RequestMapping(value = "/queryByPage",method = RequestMethod.GET)
    public Object queryByPage(AttributeQueryDto attributeQueryDto,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize){
        return new CommonResult().success(attributeService.queryEntity(pageNum,pageSize,attributeQueryDto));
    }

    /**
     * 根据id修改商品参数
     */
    @ApiOperation(value = "根据id修改商品参数")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST )
    public Object updateAttribute(@RequestBody Attribute attribute,
                                    @PathVariable Integer id){
        UpdateWrapper<Attribute> updateWrapper = new UpdateWrapper<>();
        attributeService.update(attribute,updateWrapper.eq("id",id));
        return new CommonResult().success();
    }

    /**
     * 根据id删除商品参数
     */
    @ApiOperation(value = "根据id删除商品参数")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET )
    public Object removeAttribute(@PathVariable Integer id){
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<>();
        attributeService.remove(queryWrapper.eq("id",id));
        return new CommonResult().success();
    }

    @ApiOperation(value = "根据类型Id查询商品参数")
    @RequestMapping(value = "/queryByCateId/{cateId}", method = RequestMethod.GET)
    public Object queryByCateId(@PathVariable(value = "cateId") Integer cateId){
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Attribute::getAttributeCategoryId, cateId);
        List<Attribute> attributes = attributeService.list(queryWrapper);
        return new CommonResult().success(attributes);
    }

    @RequestMapping(value = "query/{id}", method = RequestMethod.GET)
    public Object query(@PathVariable(value = "id") Integer id){
        Attribute attribute = attributeService.getById(id);
        return new CommonResult().success(attribute);
    }

}
