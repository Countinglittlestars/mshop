package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.FootprintMapper;
import com.skymall.domain.Footprint;
import com.skymall.service.impl.FootprintServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linchusen
 * @date 2019/3/15
 * 足迹管理
 */
@Api(description = "足迹管理")
@RestController
public class FootprintController {

    @Resource
    private FootprintServiceImpl footprintService;

    /**
     * 新增足迹
     * @param footprint
     * @return
     */
    @ApiOperation(value = "新增足迹")
    @RequestMapping(value = "/addFootprint",method = RequestMethod.POST )
    public Object addFootprint(@RequestBody Footprint footprint){
        footprintService.save(footprint);
        return new CommonResult().success(footprint.getId());
    }


    /**
     * 分页查询足迹
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查询足迹")
    @RequestMapping(value = "/queryFootpring",method = RequestMethod.GET )
    public Object queryFootprintByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                           @RequestParam (name = "size" ,defaultValue = "10") Integer size){
        Page<Footprint> footprintPage = new Page<>(page,size);
        IPage<Footprint> data = footprintService.queryByPage(footprintPage);
        return new CommonResult().success(data);
    }

    /**
     *
     * 根据id删除足迹
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除足迹")
    @RequestMapping(value = "/removeFootprint",method = RequestMethod.DELETE )
    public Object removeFootprint(@RequestParam Integer id){
        QueryWrapper<Footprint> queryWrapper = new QueryWrapper<>();
        footprintService.remove(queryWrapper.eq("id",id));
        return new CommonResult().success("操作成功");
    }

}
