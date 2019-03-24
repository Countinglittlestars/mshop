package com.skymall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dto.ProductQueryDto;
import com.skymall.service.IProductService;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import com.skymall.vo.admin.ProductEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/product")
@Api(value = "ProductController|产品配置相关的接口，配置sku")
public class ProductController {

    @Resource
    IProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页信息数", required = false, dataType = "Integer"),
    })
    public Object list(ProductQueryDto queryDto,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
                         ) {
        Page page = new Page(pageNum, pageSize);
        IPage resultPage = productService.queryList(page, queryDto);
        return new CommonResult().success(resultPage);
    }

}
