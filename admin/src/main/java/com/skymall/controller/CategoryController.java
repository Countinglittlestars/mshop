package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.CategoryMapper;
import com.skymall.domain.Category;
import com.skymall.service.impl.CategoryServiceImpl;
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
public class CategoryController {

    @Resource
    private CategoryServiceImpl categoryServiceImpl;

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 新增类目
     * @param category
     * @return
     */
    @RequestMapping(value = "/addCategory",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Response addCategory(@RequestBody Category category){
        categoryServiceImpl.save(category);
        return Response.success(category.getId());
    }


    /**
     * 分页查询类目信息
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryCategoryByPage",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Response queryAllCategoryByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<Category> categoryPage = new Page<>(page,size);
//        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        IPage<Category> data = categoryMapper.selectPage(categoryPage,null);
        return Response.success(data);
    }

    /**
     * 显示所有类目
     * @return
     */
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Response queryAllCategory(){
//                Category root = new Category();
//        root.setId(0);
//        root.setName("一级分类");
//        root.setParentId(-1);
//        root.setIsShow(true);
        List<Category> list = categoryServiceImpl.list(null);
//        list.add(0,root);
        HashMap<String,Object> map = new HashMap<>();
        map.put("allCategory",list);
        return Response.success(map);
    }

    /**
     * 根据Id查询类目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryCategoryById/{id}",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Response queryCategoryById(@PathVariable Integer id){
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        List<Category> list = categoryMapper.selectList(queryWrapper.eq("id",id));
        HashMap<String,Object> map = new HashMap<>();
        map.put("category",list);
        return Response.success(map);
    }

    /**
     * 根据名称查询类目
     * @param name
     * @return
     */
    @RequestMapping(value = "/queryCategoryByName",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Response queryCategoryByName(@RequestParam String name){
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        List<Category> list = categoryMapper.selectList(queryWrapper.eq("name",name));
        HashMap<String,Object> map = new HashMap<>();
        map.put("category",list);
        return Response.success(map);
    }

    /**
     * 根据Id修改类目信息
     * @param newCategory
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateCategoryById/{id}",method = RequestMethod.PUT,produces="application/json;charset=UTF-8")
    public Response updateCategory(@RequestBody Category newCategory,
                                   @PathVariable Integer id){
        UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        categoryServiceImpl.update(newCategory,updateWrapper);
        return Response.success();
    }

    /**
     * 根据id删除类目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCategory" ,method = RequestMethod.DELETE,produces="application/json;charset=UTF-8")
    public Response deleteById(@RequestParam int id){
        categoryServiceImpl.removeById(id);
        return Response.success();
    }

}
