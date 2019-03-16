package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skymall.dao.AttributeMapper;
import com.skymall.domain.Attribute;
import com.skymall.service.impl.AttributeServiceImpl;
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
public class AttributeContorller {
    @Resource
    private AttributeServiceImpl attributeServiceImpl;
    @Resource
    private AttributeMapper attributeMapper;

    /**
     * 新增商品参数
     * @param attribute
     * @return
     */
    @RequestMapping(value = "/addAttribute",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response addAttribute(@RequestBody Attribute attribute){
        attributeServiceImpl.save(attribute);
        return Response.success(attribute.getId());
    }

    /**
     * 查询所有商品参数
     * @return
     */
    @RequestMapping(value = "/queryAttribute",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryAttribute(){
        List<Attribute> list = attributeServiceImpl.list(null);
        HashMap<String,Object> map = new HashMap<>();
        map.put("AllAttribute",list);
        return Response.success(map);
    }

    /**
     * 根据id修改商品参数
     * @param attribute
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateAttribute",method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public Response updateAttribute(@RequestBody Attribute attribute,
                                    @RequestParam Integer id){
        UpdateWrapper<Attribute> updateWrapper = new UpdateWrapper<>();
        attributeServiceImpl.update(attribute,updateWrapper.eq("id",id));
        return Response.success();
    }

    /**
     * 根据id删除商品参数
     * @param id
     */
    @RequestMapping(value = "/deleteAttribute",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeAttribute(@RequestParam Integer id){
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<>();
        attributeServiceImpl.remove(queryWrapper.eq("id",id));
        return Response.success();
    }
}
