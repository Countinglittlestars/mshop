package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.FootprintMapper;
import com.skymall.domain.Footprint;
import com.skymall.service.impl.FootprintServiceImpl;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author linchusen
 * @date 2019/3/15
 * 足迹管理
 */
public class FootprintController {
    @Resource
    private FootprintMapper footprintMapper;
    @Resource
    private FootprintServiceImpl footprintServiceImp;

    /**
     * 新增足迹
     * @param footprint
     * @return
     */
    @RequestMapping(value = "/addFootprint",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response addFootprint(@RequestBody Footprint footprint){
        footprintServiceImp.save(footprint);
        return Response.success(footprint.getId());
    }


    /**
     * 根据用户Id分页查询足迹
     * @param page
     * @param size
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryFootpring",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryFootprintByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                           @RequestParam (name = "size" ,defaultValue = "10") Integer size,
                                           @RequestParam Integer userId){
        Page<Footprint> footprintPage = new Page<>(page,size);
        QueryWrapper<Footprint> queryWrapper = new QueryWrapper<>();
        IPage<Footprint> data = footprintMapper.selectPage(footprintPage,queryWrapper.eq("user_id",userId));
        return Response.success(data);
    }

    /**
     *
     * 根据id删除足迹
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeFootprint",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeFootprint(@RequestParam Integer id){
        QueryWrapper<Footprint> queryWrapper = new QueryWrapper<>();
        footprintServiceImp.remove(queryWrapper.eq("id",id));
        return Response.success();
    }

}
