package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skymall.domain.Brand;
import com.skymall.dto.BrandAddDto;
import com.skymall.dto.BrandQueryDto;
import com.skymall.enums.ExceptionEnums;
import com.skymall.exception.AdminException;
import com.skymall.service.IBrandService;
import com.skymall.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author linchusen
 * @since 2019/03/14
 *
 * 品牌管理
 */
@Api(description = "品牌管理")
@RestController
@RequestMapping(value="/admin/brand")
public class BrandController {
    @Resource
    private IBrandService brandService;

    /**
     * 新增品牌
     */
    @ApiOperation(value = "新增收货地址",notes = "品牌名称不能重复")
    @RequestMapping(value = "/add",method = RequestMethod.POST )
    public Object addBrand(@RequestBody BrandAddDto brandAddDto){

        Brand brand = brandService.getOne
                (new QueryWrapper<Brand>().lambda().eq(Brand::getName, brandAddDto.getName()));
        if (brand == null){
            throw new AdminException(ExceptionEnums.NOTUNIQUE);
        }
        brandService.addBrand(brandAddDto);
        return new CommonResult().success();
    }

    /**
     * 分页查询所有品牌
     */
    @ApiOperation(value = "分页查询所有品牌",notes = "默认每页10条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页信息数", required = false, dataType = "Integer")
    })
    @RequestMapping(value = "/queryByPage", method = RequestMethod.GET)
    public Object queryAllBrandByPage(BrandQueryDto brandQueryDto,
            @RequestParam (value = "pageNum", defaultValue = "1") Integer page,
            @RequestParam (value = "pageSize", defaultValue = "5") Integer size){
////        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
////        此处的Page来自com.baomidou.mybatisplus.extension.plugins.pagination.Page，若导错包会报错
//        Page<Brand> brandPage = new Page<>(page,size);
//        IPage<Brand> data = brandService.queryByPage(brandPage);
        return new CommonResult().success(brandService.queryBrandByPage(brandQueryDto,page,size));
    }

    /**
     * 查询所有品牌
     */
    @ApiOperation(value = "查询所有品牌")
    @RequestMapping(value = "/queryBrand", method = RequestMethod.GET )
    public Object queryBrand(){
        List<Brand> list = brandService.list(null);
        return new CommonResult().success(list);
    }

    /**
     * 根据Id查询品牌信息
     */
    @ApiOperation(value = "根据Id查询品牌信息",notes = "根据品牌Id查询")
    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET )
    public Object queryBrandById(@PathVariable Integer id){
        BrandQueryDto brand = brandService.queryBrandById(id);
        return new CommonResult().success(brand);
    }

    /**
     * 根据id修改品牌信息
     */
    @ApiOperation(value = "根据id修改品牌信息",notes = "根据品牌Id修改")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST )
    public Object updateBrandById(@RequestBody Brand brand,
                                    @PathVariable Integer id){
        //先查询，如果查不到，就报错
        if(brandService.getById(id) == null) throw new AdminException("根据id找不到品牌信息", 500);
        brandService.update(brand,new UpdateWrapper<Brand>().lambda().eq(Brand::getId,id));
        return new CommonResult().success();
    }

    /**
     * 根据Id删除品牌
     */
    @ApiOperation(value = "根据Id删除品牌",notes = "根据品牌Id删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE )
    public Object removeBrandById(@PathVariable Integer id){

        Brand brand = brandService.getById(id);
        if (brand == null) {
            throw  new AdminException(ExceptionEnums.NOTFOUND);
        }else{
            brandService.remove(new QueryWrapper<Brand>().lambda().eq(Brand::getId, id));
            return new CommonResult().success();

            }
        }


}
