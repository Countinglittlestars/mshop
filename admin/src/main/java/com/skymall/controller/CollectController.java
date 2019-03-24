package com.skymall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.CollectMapper;
import com.skymall.domain.Collect;
import com.skymall.service.impl.CollectServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linchusen
 * @date 2019/3/14
 * 收藏管理
 */
@Api(description = "收藏管理")
@RestController
public class CollectController {

    @Resource
    private CollectServiceImpl collectService;

    /**
     * 添加收藏
     */
    @ApiOperation(value = "添加收藏")
    @RequestMapping(value = "/add",method = RequestMethod.POST )
    public Object addCollect(@RequestBody Collect collect){
        collectService.save(collect);
        return new CommonResult().success(collect.getId());
    }

    /**
     *
     * 根据用户Id查询收藏列表
     */
    @ApiOperation(value = "根据用户Id查询收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页信息数", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer")
    })
    @RequestMapping(value = "/queryByUserId/{userId}",method = RequestMethod.GET)
    public Object queryCollectByPage(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                       @RequestParam (name = "size",defaultValue = "10") Integer size,
                                       @PathVariable Integer userId){
        Page<Collect> collectPage = new Page<>(page,size);
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        IPage<Collect> data = collectService.pageByExample(collectPage,queryWrapper.eq("user_id",userId));
        return new CommonResult().success(data);
    }

    /**
     * 分页查询所有收藏信息
     */
    @ApiOperation(value = "分页查询所有收藏信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页信息数", required = false, dataType = "Integer")
    })
    @RequestMapping(value = "/query",method = RequestMethod.GET )
    public Object queryAllCollectByPage(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                          @RequestParam (name = "size",defaultValue = "10") Integer size){
        Page<Collect> cartPage = new Page<>(page,size);
        IPage<Collect> data = collectService.queryByPage(cartPage);
        return new CommonResult().success(data);
    }

    /**
     * 根据id删除收藏信息
     */
    @ApiOperation(value = "根据id删除收藏信息")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE )
    public Object removeCollect(@RequestParam Integer id){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        collectService.remove(queryWrapper.eq("id",id));
        return new CommonResult().success();
    }
}
