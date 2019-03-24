package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skymall.dao.AttributeMapper;
import com.skymall.domain.Attribute;
import com.skymall.service.impl.AttributeServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
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
     * @param attribute
     * @return
     */
    @ApiOperation(value = "新增商品参数")
    @RequestMapping(value = "/addAttribute",method = RequestMethod.POST )
    @ApiImplicitParam(name = "id", value = "参数详细实体attribute", paramType = "Attribute" )
    public Object addAttribute(@RequestBody Attribute attribute){
        attributeService.save(attribute);
        return new CommonResult().success(attribute.getId());
    }

    /**
     * 查询所有商品参数
     * @return
     */
    @ApiOperation(value = "查询所有商品参数")
    @RequestMapping(value = "/queryAttribute",method = RequestMethod.GET )
    public Object queryAttribute(){
        List<Attribute> list = attributeService.list(null);
        return new CommonResult().success(list);
    }

    /**
     * 根据id修改商品参数
     * @param attribute
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id修改商品参数")
    @RequestMapping(value = "/updateAttribute",method = RequestMethod.PUT )
    public Object updateAttribute(@RequestBody Attribute attribute,
                                    @RequestParam Integer id){
        UpdateWrapper<Attribute> updateWrapper = new UpdateWrapper<>();
        attributeService.update(attribute,updateWrapper.eq("id",id));
        return new CommonResult().success("操作成功");
    }

    /**
     * 根据id删除商品参数
     * @param id
     */
    @ApiOperation(value = "根据id删除商品参数")
    @RequestMapping(value = "/deleteAttribute",method = RequestMethod.DELETE )
    public Object removeAttribute(@RequestParam Integer id){
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<>();
        attributeService.remove(queryWrapper.eq("id",id));
        return new CommonResult().success("删除成功");
    }

    @ApiOperation(value = "根据类型Id查询商品参数")
    @RequestMapping(value = "/queryByCateId/{cateId}", method = RequestMethod.GET)
    public Object queryByCateId(@PathVariable(value = "cateId") Integer cateId){
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Attribute::getAttributeCategoryId, cateId);
        List<Attribute> attributes = attributeService.list(queryWrapper);
        return new CommonResult().success(attributes);
    }



}
