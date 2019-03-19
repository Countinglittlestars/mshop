package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.Category;
import com.skymall.domain.CategoryWithChildrenItem;
import com.skymall.service.impl.CategoryServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
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


@RestController
@RequestMapping(value="/admin/category")
public class CategoryController {

    @Resource
    private CategoryServiceImpl categoryService;

    /**
     * 新增类目
     * @param category
     * @return
     */
    @RequestMapping(value = "/addCategory",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addCategory(@RequestBody Category category){
        categoryService.save(category);
        return new CommonResult().success(category.getId());
    }


    /**
     * 分页查询类目信息
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryCategoryByPage",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Object queryAllCategoryByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<Category> categoryPage = new Page<>(page,size);
        IPage<Category> data = categoryService.queryByPage(categoryPage);
        return new CommonResult().success(data);
    }

    /**
     * 显示所有类目
     * @return
     */
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
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
     * @return
     */
    @RequestMapping(value = "/queryWithChildren",method = RequestMethod.GET)
    public Object listWhitChildren(){

        List<CategoryWithChildrenItem> categoryWithChildrenItem  =  categoryService.listWhitChildren();
        return new CommonResult().success(categoryWithChildrenItem);
    }




    /**
     * 根据父分类查到所有的子分类
     */
    @RequestMapping(value="/queryByParentId/{parentId}", method = RequestMethod.GET)
    public Object queryByParentId(@PathVariable Integer parentId){

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Category::getParentId, parentId);
        List<Category> list = categoryService.list(queryWrapper);
        return new CommonResult().success(list);
    }








    /**
     * 根据Id查询类目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryCategoryById/{id}",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Object queryCategoryById(@PathVariable Integer id){
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        List<Category> list = categoryService.list(queryWrapper.eq("id",id));
        return new CommonResult().success(list);
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
    @RequestMapping(value = "/updateCategoryById/{id}",method = RequestMethod.PUT,produces="application/json;charset=UTF-8")
    public Object updateCategory(@RequestBody Category newCategory,
                                   @PathVariable Integer id){
        UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        categoryService.update(newCategory,updateWrapper);
        return new CommonResult().success("操作成功");
    }

    /**
     * 根据id删除类目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCategory" ,method = RequestMethod.DELETE,produces="application/json;charset=UTF-8")
    public Object deleteById(@RequestParam int id){
        categoryService.removeById(id);
        return new CommonResult().success("操作成功");
    }

}
