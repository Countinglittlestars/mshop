package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AttributeCategoryMapper;
import com.skymall.domain.AttributeCategory;
import com.skymall.service.IAttributeCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service("IAttributeCategoryService")
public class AttributeCategoryServiceImpl extends ServiceImpl<AttributeCategoryMapper, AttributeCategory> implements IAttributeCategoryService {
        @Resource
    private AttributeCategoryMapper attributeCategoryMapper;


    @Override
    public IPage<AttributeCategory> queryByPage(Integer page,Integer size,AttributeCategory attributeCategory) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (null != attributeCategory.getId()){
            queryWrapper.eq("id",attributeCategory.getId());
        }
        if (null != attributeCategory.getName()){
            queryWrapper.like("name",attributeCategory.getName());
        }
        Page page1 = new Page(page,size);
        return attributeCategoryMapper.selectMapsPage(page1,queryWrapper);
    }

    @Override
    public List<AttributeCategory> queryEnableEnabledAttrCate() {
        return attributeCategoryMapper.selectList
                (new QueryWrapper<AttributeCategory>().lambda().eq(AttributeCategory::getEnabled,"1"));
    }

}
