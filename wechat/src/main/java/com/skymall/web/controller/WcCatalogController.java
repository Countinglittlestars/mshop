package com.skymall.web.controller;

import com.skymall.domain.Category;
import com.skymall.service.IWcCatagoryService;
import com.skymall.utils.BeanUtils;
import com.skymall.vo.Response;
import com.skymall.web.dto.requestDto.CategroyReqDto;
import com.skymall.web.dto.responseDto.CategroyRspDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "mshop/wc/catalog")
public class WcCatalogController {

    @Resource
    IWcCatagoryService wcCatagoryService;

    @RequestMapping(value = "index", method = RequestMethod.POST)
    public Response index(@RequestParam(value = "id", required = false) Integer id,
                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size
                        ){
        CategroyReqDto categroyReqDto = new CategroyReqDto();
        categroyReqDto.setParentId(0);
        List<Category> data = wcCatagoryService.selectAllByPage(categroyReqDto, page, size);
        //因为传过前端的数据是有子分类的，但是数据库的表设计是没有的，所以要做ResponseDto的封装
        CategroyRspDto currentCategory = new CategroyRspDto();
        //先取得原本数据，后面要加上子分类，然后全部传递给RspDto
        Category category = null;
        if(null != id) {
            category = wcCatagoryService.selectById(id);
        }
        if(null == category && null != data && data.size() != 0) {
            category = data.get(0);
        }else {
            category = new Category();
        }
        //获取子分类数据
        if(null != category && null != category.getId()) {
            categroyReqDto.setParentId(category.getId());
            BeanUtils.mapping(category, currentCategory);
            currentCategory.setSubCategoryList(wcCatagoryService.selectAllByPage(categroyReqDto, page, size));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("categoryList", data);
        map.put("currentCategory", currentCategory);
        return Response.success(map);
    }

    @RequestMapping(value = "/current", method = RequestMethod.POST)
    public Response current(@RequestParam(value = "id", required = true) Integer id) {

        Category category = wcCatagoryService.selectById(id);
        if(null == category) {
            return Response.success(category);
        }
        //获取子类型数据
        List<Category> categories = wcCatagoryService.selectChildren(category.getId());
        CategroyRspDto categroyRspDto = new CategroyRspDto();
        BeanUtils.mapping(category, categroyRspDto);
        categroyRspDto.setSubCategoryList(categories);
        return Response.success(categroyRspDto);
    }
}
