package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.Specification;
import com.skymall.service.ISpecificationService;
import com.skymall.vo.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linchusen
 * 规格管理
 */
@RestController
@RequestMapping(value = "/admin/specification")
public class SpecificationController {

    @Resource
    private ISpecificationService specificationService;


    /**
     * 新增规格
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSpec(@RequestBody Specification specification){
        specificationService.save(specification);
        return new CommonResult().success();
    }



    /**
     * 根据规格名称，id分页查询规格信息
     */
    @RequestMapping(value = "/queryByPage",method = RequestMethod.GET)
    public Object querySpec(Specification specification,
                            @RequestParam(defaultValue =  "1") Integer page,
                            @RequestParam(defaultValue =  "10") Integer size){
        Page page1 = new Page(page,size);
        return new CommonResult().success(specificationService.querySpec(page1,specification));
    }

    /**
     * 根据规格id查询规格信息
     */
    @RequestMapping(value = "/queryById/{id}",method = RequestMethod.GET)
    public Object querySpecById(@PathVariable Integer id){
        return new CommonResult().success(specificationService.getById(id));
    }

    /**
     * 查询所有规格
     */
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public Object queryAll(){
        return new CommonResult().success(specificationService.list());
    }

    /**
     * 根据id和规格信息修改规格
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Object updateSpec(@RequestBody Specification specification,
                             @PathVariable Integer id){

        specificationService.update
                (specification,new UpdateWrapper<Specification>().lambda().eq(Specification::getId,id));
        return new CommonResult().success();
    }

    /**
     * 根据id删除规格
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id){
        return new CommonResult().success
                (specificationService.remove(new QueryWrapper<Specification>().lambda().eq(Specification::getId,id)));
    }

}
