package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skymall.dao.AttributeMapper;
import com.skymall.domain.Attribute;
import com.skymall.service.IAttributeService;
import com.skymall.service.impl.AttributeServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
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

@RestController
@RequestMapping(value = "/admin/attribute")
public class AttributeContorller {
    @Resource
    private IAttributeService attributeService;



    /**
     * 根据属性类型id找到所有的属性
     */
    @RequestMapping(value = "/queryByCateId/{id}", method = RequestMethod.GET)
    public Object queryByCateId(@PathVariable Integer id){
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Attribute::getAttributeCategoryId, id);
        List list = attributeService.list(queryWrapper);
        return new CommonResult().success(list);
    }


    /**
     * 新增商品参数
     * @param attribute
     * @return
     */
    @RequestMapping(value = "/addAttribute",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Object addAttribute(@RequestBody Attribute attribute){
        attributeService.save(attribute);
        return new CommonResult().success(attribute.getId());
    }

    /**
     * 查询所有商品参数
     * @return
     */
    @RequestMapping(value = "/queryAttribute",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Object queryAttribute(){
        List<Attribute> list = attributeService.list(null);
        HashMap<String,Object> map = new HashMap<>();
        map.put("AllAttribute",list);
        return new CommonResult().success(map);
    }

    /**
     * 根据id修改商品参数
     * @param attribute
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateAttribute",method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public Object updateAttribute(@RequestBody Attribute attribute,
                                    @RequestParam Integer id){
        UpdateWrapper<Attribute> updateWrapper = new UpdateWrapper<>();
        attributeService.update(attribute,updateWrapper.eq("id",id));
        return Response.success();
    }

    /**
     * 根据id删除商品参数
     * @param id
     */
    @RequestMapping(value = "/deleteAttribute",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeAttribute(@RequestParam Integer id){
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<>();
        attributeService.remove(queryWrapper.eq("id",id));
        return Response.success();
    }
}
