package com.skymall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.CollectMapper;
import com.skymall.domain.Collect;
import com.skymall.service.impl.CollectServiceImpl;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linchusen
 * @date 2019/3/14
 * 收藏管理
 */
@RestController
public class CollectController {
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private CollectServiceImpl collectServiceImpl;

    /**
     * 添加收藏
     * @param collect
     * @return
     */
    @RequestMapping(value = "/addCollect",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response addCollect(@RequestBody Collect collect){
        collectServiceImpl.save(collect);
        return Response.success(collect.getId());
    }

    /**
     *
     * 根据用户Id查询收藏列表
     * @param page
     * @param size
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryCollect/{userId}",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Response queryCollectByPage(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                       @RequestParam (name = "size",defaultValue = "10") Integer size,
                                       @PathVariable Integer userId){
        Page<Collect> collectPage = new Page<>(page,size);
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        IPage<Collect> data = collectMapper.selectPage(collectPage,queryWrapper.eq("user_id",userId));
        return Response.success(data);
    }

    /**
     * 分页查询所有收藏信息
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/queryCollect",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryAllCollectByPage(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                          @RequestParam (name = "size",defaultValue = "10") Integer size){
        Page<Collect> cartPage = new Page<>(page,size);
        IPage<Collect> data = collectMapper.selectPage(cartPage,null);
        return Response.success(data);
    }

    /**
     * 根据id删除收藏信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeCollect",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeCollect(@RequestParam Integer id){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        collectServiceImpl.remove(queryWrapper.eq("id",id));
        return Response.success();
    }
}
