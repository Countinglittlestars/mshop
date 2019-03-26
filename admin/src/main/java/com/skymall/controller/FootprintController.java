package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.FootprintMapper;
import com.skymall.domain.Footprint;
import com.skymall.dto.FootPrintQueryDto;
import com.skymall.service.impl.FootprintServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.FootprintVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/admin/footPrint")
public class FootprintController {

    @Resource
    private FootprintServiceImpl footprintService;

    /**
     * 新增足迹
     */
    @ApiOperation(value = "新增足迹")
    @RequestMapping(value = "/addFootprint",method = RequestMethod.POST )
    public Object addFootprint(@RequestBody Footprint footprint){
        footprintService.save(footprint);
        return new CommonResult().success(footprint.getId());
    }


    /**
     * 分页查询足迹
     */
    @ApiOperation(value = "分页查询足迹")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页信息数", required = false, dataType = "Integer"),
    })
    @RequestMapping(value = "/query",method = RequestMethod.GET )
    public Object queryFootprintByUserId( FootPrintQueryDto footPrintQueryDto,
                                            @RequestParam (name = "pageNum",defaultValue = "1") Integer page,
                                           @RequestParam (name = "pageSize" ,defaultValue = "5") Integer size){
        Page<FootprintVo> footprintPage = new Page<>(page,size);
        IPage<FootprintVo> data = footprintService.queryByPage(footprintPage, footPrintQueryDto);
        return new CommonResult().success(data);
    }

    /**
     * 根据id删除足迹
     */
    @ApiOperation(value = "根据id删除足迹")
    @ApiImplicitParam(paramType="delete", name = "id", value = "足迹Id", required = true, dataType = "Integer")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE )
    public Object removeFootprint(@RequestParam Integer id){
        QueryWrapper<Footprint> queryWrapper = new QueryWrapper<>();
        footprintService.remove(queryWrapper.eq("id",id));
        return new CommonResult().success();
    }

}
