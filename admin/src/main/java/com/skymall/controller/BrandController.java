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
@RequestMapping(value="/admin/brand")
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
    @RequestMapping(value = "/queryBrandByPage", method = RequestMethod.GET)
    public Object queryAllBrandByPage(@RequestParam (value = "page", defaultValue = "1") Integer page,
                                        @RequestParam (value = "size", defaultValue = "10") Integer size){
//        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
//        此处的Page来自com.baomidou.mybatisplus.extension.plugins.pagination.Page，若导错包会报错
        Page<Brand> brandPage = new Page<>(page,size);
        IPage<Brand> data = brandService.queryByPage(brandPage);
        return new CommonResult().success(data);
    }

    /**
     * 查询所有品牌
     * @return
     */
    @RequestMapping(value = "/queryBrand", method = RequestMethod.GET )
    public Object queryBrand(){
        List<Brand> list = brandService.list(null);
        return new CommonResult().success(list);
    }

    /**
     * 根据Id查询品牌信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryBrandById/{id}", method = RequestMethod.GET )
    public Object queryBrandById(@PathVariable Integer id){
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        List<Brand> list = brandService.list(queryWrapper.eq("id",id));
        return new CommonResult().success(list);
    }

    /**
     * 根据id修改品牌信息
     * @param brand
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateBrand", method = RequestMethod.PUT )
    public Object updateBrandById(@RequestBody Brand brand,
                                    @RequestParam Integer id){
        UpdateWrapper<Brand> updateWrapper = new UpdateWrapper<>();
        brandService.update(brand,updateWrapper.eq("id",id));
        return new CommonResult().success("操作成功");
    }

    /**
     * 根据Id删除品牌
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeBrand", method = RequestMethod.DELETE )
    public Object removeBrandById(@RequestParam Integer id){
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        brandService.remove(queryWrapper.eq("id",id));
        return new CommonResult().success("删除成功");
    }

}
