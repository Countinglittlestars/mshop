package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.CategoryMapper;
import com.skymall.domain.Category;
import com.skymall.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linchusen
 * @since 2019-03-12
 */



@Service("Category")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 分页查询
     * @param page
     * @return
     */
    public IPage<Category> queryByPage(Page<Category> page) {
        return categoryMapper.selectPage(page, null);
    }


}
