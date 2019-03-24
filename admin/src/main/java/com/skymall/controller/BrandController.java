package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.Brand;
import com.skymall.dto.BrandAddDto;
import com.skymall.enums.ExceptionEnums;
import com.skymall.exception.ApiRRException;
import com.skymall.service.IBrandService;
import com.skymall.vo.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
     */
    @RequestMapping(value = "/addBrand",method = RequestMethod.POST )
    public Object addBrand(@RequestBody BrandAddDto brandAddDto){

        Brand brand = brandService.getOne
                (new QueryWrapper<Brand>().lambda().eq(Brand::getName,brandAddDto.getName()));
        if (brand != null){
            throw new ApiRRException(ExceptionEnums.NOTUNIQUE);
        }
        brandService.addBrand(brandAddDto);
        Brand newBrand = brandService.getOne
                (new QueryWrapper<Brand>().lambda().eq(Brand::getName,brandAddDto.getName()));
        return new CommonResult().success(newBrand.getId());
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
        List<Brand> list = brandService.list(new QueryWrapper<Brand>().lambda().eq(Brand::getId,id));
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
        brandService.update(brand,new UpdateWrapper<Brand>().lambda().eq(Brand::getId,id));
        return new CommonResult().success("操作成功");
    }

    /**
     * 根据Id删除品牌
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeBrand/{id}", method = RequestMethod.DELETE )
    public Object removeBrandById(@PathVariable Integer id){

        Brand brand = brandService.getById(id);
        if (brand == null) {
            throw  new ApiRRException(ExceptionEnums.NOTFOUND);
        }else{
            brandService.remove(new QueryWrapper<Brand>().lambda().eq(Brand::getId, id));
            return new CommonResult().success();

            }
        }


}
