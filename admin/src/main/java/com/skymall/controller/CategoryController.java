package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Collections2;
import com.skymall.domain.Category;
import com.skymall.domain.CategoryWithChildrenItem;
import com.skymall.exception.AdminException;
import com.skymall.service.impl.CategoryServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author linchusen
 * @date  2019/3/12
 *
 * 类目管理
 *
 */

@Api(description = "类目管理")
@RestController
@RequestMapping(value="/admin/category")
public class CategoryController {

    @Resource
    private CategoryServiceImpl categoryService;

    /**
     * 新增类目
     */
    @ApiOperation(value = "新增类目")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCategory(@RequestBody Category category){
        if(category.getParentId() == 0){
            category.setLevel("L1");
        }else category.setLevel("L2");
        categoryService.save(category);
        return new CommonResult().success();
    }


    /**
     * 分页查询类目信息
     */
    @ApiOperation(value = "分页查询类目信息")
    @RequestMapping(value = "/queryByPage",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "page", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "size", value = "每页信息数", required = false, dataType = "Integer")
    })
    public Object queryAllCategoryByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<Category> categoryPage = new Page<>(page,size);
        IPage<Category> data = categoryService.queryByPage(categoryPage);
        return new CommonResult().success(data);
    }

    /**
     * 显示所有类目
     */
    @ApiOperation(value = "显示所有类目")
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public Object queryAllCategory(){
//                Category root = new Category();
//        root.setId(0);
//        root.setName("一级分类");
//        root.setParentId(-1);
//        root.setIsShow(true);
        List<Category> list = categoryService.list(null);
//        list.add(0,root);
        HashMap<String,Object> map = new HashMap<>();
        map.put("allCategory",list);
        return new CommonResult().success(map);
    }


    /**
     * 查找分类列表，格式为：一级分类下有list二级分类
     */
    @ApiOperation(value = "查找分类列表，格式为：一级分类下有list二级分类")
    @RequestMapping(value = "/queryWithChildren",method = RequestMethod.GET)
    public Object listWhitChildren(){

        List<CategoryWithChildrenItem> categoryWithChildrenItem  =  categoryService.listWhitChildren();
        return new CommonResult().success(categoryWithChildrenItem);
    }




    /**
     * 根据父分类查到所有的子分类
     */
    @ApiOperation(value = "根据父分类查到所有的子分类")
    @RequestMapping(value="/queryByParentId/{parentId}", method = RequestMethod.GET)
    public Object queryByParentId(@PathVariable Integer parentId,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
                                  ){
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Category::getParentId, parentId).orderByAsc(Category::getSortOrder);
        IPage page1 = categoryService.page(page, queryWrapper);
        return new CommonResult().success(page1);
    }








    /**
     * 根据Id查询类目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryCategoryById/{id}",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Object queryCategoryById(@PathVariable Integer id){
        Category category = categoryService.getById(id);
        return new CommonResult().success(category);
    }

    /**
     * 根据名称查询类目
     * @param name
     * @return
     */
    @RequestMapping(value = "/queryCategoryByName",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Object queryCategoryByName(@RequestParam String name){
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        List<Category> list = categoryService.list(queryWrapper.eq("name",name));
        return new CommonResult().success(list);
    }

    /**
     * 根据Id修改类目信息
     * @param newCategory
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateCategoryById/{id}",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateCategory(@RequestBody Category newCategory,
                                   @PathVariable Integer id){
        UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        if(newCategory.getParentId() == 0){
            newCategory.setLevel("L1");
        }else newCategory.setLevel("L2");
        Boolean flag = categoryService.update(newCategory,updateWrapper);
        if(flag == true) {return new CommonResult().success("操作成功");}
        else return new CommonResult().failed();
    }

    /**
     * 根据id删除类目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCategory/{id}" ,method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Object deleteById(@PathVariable int id){
        //如果下面有子分类，则删除失败
        if(!Collections.isEmpty(categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getParentId, id)))){
            throw new AdminException("还存在子分类，删除失败", 500);
        }

        categoryService.removeById(id);
        return new CommonResult().success("操作成功");
    }

}
