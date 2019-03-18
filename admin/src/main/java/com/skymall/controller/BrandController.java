package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.BrandMapper;
import com.skymall.domain.Brand;
import com.skymall.service.IBrandService;
import com.skymall.service.impl.BrandServiceImpl;
import com.skymall.vo.CommonResult;
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
    private IBrandService brandService;

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @RequestMapping(value = "/addBrand",method = RequestMethod.POST )
    public Response addBrand(@RequestBody Brand brand){
        brandService.save(brand);
        return Response.success(brand.getId());
    }

    /**
     * 分页查询所有品牌
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/queryBrandByPage", method = RequestMethod.GET )
    public Response queryAllBrandByPage(@RequestParam (value = "page", defaultValue = "1") Integer page,
                                        @RequestParam (value = "size", defaultValue = "10") Integer size){
//        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
//        此处的Page来自com.baomidou.mybatisplus.extension.plugins.pagination.Page，若导错包会报错
        Page<Brand> brandPage = new Page<>(page,size);
        IPage<Brand> data = brandService.queryByPage(brandPage);
        return Response.success(data);
    }

    /**
     * 查询所有品牌
     * @return
     */
    @RequestMapping(value = "/queryBrand", method = RequestMethod.GET )
    public Object queryBrand(){
        List<Brand> list = brandService.list(null);
        HashMap<String,Object> map = new HashMap<>();
        map.put("Brand",list);
        CommonResult common = new CommonResult().success(map);
        return common;

    }

    /**
     * 根据Id查询品牌信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryBrandById", method = RequestMethod.GET )
    public Response queryBrandById(@RequestParam Integer id){
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        List<Brand> list = brandService.list(queryWrapper.eq("id",id));
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
    @RequestMapping(value = "/updateBrand", method = RequestMethod.PUT )
    public Response updateBrandById(@RequestBody Brand brand,
                                    @RequestParam Integer id){
        UpdateWrapper<Brand> updateWrapper = new UpdateWrapper<>();
        brandService.update(brand,updateWrapper.eq("id",id));
        return Response.success();
    }

    /**
     * 根据Id删除品牌
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeBrand", method = RequestMethod.DELETE )
    public Response removeBrandById(@RequestParam Integer id){
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        brandService.remove(queryWrapper.eq("id",id));
        return Response.success();
    }

}
