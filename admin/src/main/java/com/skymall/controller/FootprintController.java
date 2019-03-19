package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.FootprintMapper;
import com.skymall.domain.Footprint;
import com.skymall.service.impl.FootprintServiceImpl;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linchusen
 * @date 2019/3/15
 * 足迹管理
 */
@RestController
public class FootprintController {

    @Resource
    private FootprintServiceImpl footprintService;

    /**
     * 新增足迹
     * @param footprint
     * @return
     */
    @RequestMapping(value = "/addFootprint",method = RequestMethod.POST )
    public Response addFootprint(@RequestBody Footprint footprint){
        footprintService.save(footprint);
        return Response.success(footprint.getId());
    }


    /**
     * 分页查询足迹
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/queryFootpring",method = RequestMethod.GET )
    public Response queryFootprintByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                           @RequestParam (name = "size" ,defaultValue = "10") Integer size){
        Page<Footprint> footprintPage = new Page<>(page,size);
        IPage<Footprint> data = footprintService.queryByPage(footprintPage);
        return Response.success(data);
    }

    /**
     *
     * 根据id删除足迹
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeFootprint",method = RequestMethod.DELETE )
    public Response removeFootprint(@RequestParam Integer id){
        QueryWrapper<Footprint> queryWrapper = new QueryWrapper<>();
        footprintService.remove(queryWrapper.eq("id",id));
        return Response.success();
    }

}
