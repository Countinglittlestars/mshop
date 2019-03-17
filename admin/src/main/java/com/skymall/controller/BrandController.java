package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.BrandMapper;
import com.skymall.domain.Brand;
import com.skymall.service.impl.BrandServiceImpl;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


/**
 * @author linchusen
 * @date 2019/03/14
 *
 * 品牌管理
 */
@RestController
public class BrandController {
    @Resource
    private BrandServiceImpl brandServiceImpl;
    @Resource
    private BrandMapper brandMapper;






    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @RequestMapping(value = "/addBrand",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response addBrand(@RequestBody Brand brand){
        brandServiceImpl.save(brand);
        return Response.success(brand.getId());
    }

    /**
     * 分页查询所有品牌
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/queryBrandByPage", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryAllBrandByPage(@RequestParam (value = "page", defaultValue = "1") Integer page,
                                        @RequestParam (value = "size", defaultValue = "10") Integer size){
//        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
//        此处的Page来自com.baomidou.mybatisplus.extension.plugins.pagination.Page，若导错包会报错
        Page<Brand> brandPage = new Page<>(page,size);
        IPage<Brand> data = brandMapper.selectPage(brandPage,null);
        return Response.success(data);
    }

    /**
     * 根据Id查询品牌信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryBrandById", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryBrandById(@RequestParam Integer id){
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        List<Brand> list = brandServiceImpl.list(queryWrapper.eq("id",id));
        HashMap<String,Object> map = new HashMap<>();
        map.put("brand",list);
        return Response.success(map);
    }

    /**
     * 根据id修改品牌信息
     * @param brand
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateBrand", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public Response updateBrandById(@RequestBody Brand brand,
                                    @RequestParam Integer id){
        UpdateWrapper<Brand> updateWrapper = new UpdateWrapper<>();
        brandServiceImpl.update(brand,updateWrapper.eq("id",id));
        return Response.success();
    }

    /**
     * 根据Id删除品牌
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeBrand", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeBrandById(@RequestParam Integer id){
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        brandServiceImpl.remove(queryWrapper.eq("id",id));
        return Response.success();
    }

}
