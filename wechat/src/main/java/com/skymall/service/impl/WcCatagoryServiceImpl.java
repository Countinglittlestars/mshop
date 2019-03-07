package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.CategoryMapper;
import com.skymall.domain.Category;
import com.skymall.exception.ApiRRException;
import com.skymall.service.AbstractService;
import com.skymall.service.IWcCatagoryService;
import com.skymall.utils.WrapperUtil;
import com.skymall.web.dto.requestDto.CategroyReqDto;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static jdk.nashorn.api.scripting.ScriptObjectMirror.wrap;

@Service
public class WcCatagoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements IWcCatagoryService {

    @Resource
    CategoryMapper categoryMapper;

    /**
     * 查询指定的列的内容，如果是id,则column为id，  如果多列则为 "column,username, password"
     * @param categroyReqDto
     * @param column
     * @return
     */
    private List<Object> listSelectColumn(CategroyReqDto categroyReqDto, String column){
        QueryWrapper queryWrappr = new QueryWrapper();
        queryWrappr.select("select " + column);
        WrapperUtil.wrap(queryWrappr, categroyReqDto);
        List list = categoryMapper.selectList(queryWrappr);
        return list;
    }

    /**
     * 根据查询指定ID数组下的所有分类
     * @param
     * @return
     */
    private List<Object> listByIds(Integer[] ids){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id", ids);
        List list = categoryMapper.selectList(queryWrapper);
        return list;

    }



    @Override
    public List<Category> selectAll(CategroyReqDto categroyReqDto) {
        QueryWrapper entityWrapper = new QueryWrapper();
        WrapperUtil.wrap(entityWrapper, categroyReqDto);
        List<Category> list = categoryMapper.selectList(entityWrapper);
        return list;
    }
    @Override
    public List<Category> selectAllByPage(CategroyReqDto categroyReqDto, Integer page, Integer size){

        QueryWrapper entityWrapper = new QueryWrapper();
        WrapperUtil.wrap(entityWrapper, categroyReqDto);
        if(page == null || page == null){
            List<Category> categoryList = categoryMapper.selectList(entityWrapper);
            return categoryList;
        }
        Page<Category> pager = new Page<Category>(page, size);
        List categoryList =  categoryMapper.selectPage(pager, entityWrapper).getRecords();
        return categoryList;
    }

    @Override
    public Category selectById(Integer id) {
        Category category = categoryMapper.selectById(id);
        return category;
    }

    @Override
    public List<Category> selectChildren(Integer parentId) {
        if(parentId == null){
            throw new ApiRRException("parentId不能为空",500);
        }
        QueryWrapper entityWrapper = new QueryWrapper();
        entityWrapper.eq("parent_id", parentId);
        List<Category> categories = categoryMapper.selectList(entityWrapper);
        return categories;
    }


}
