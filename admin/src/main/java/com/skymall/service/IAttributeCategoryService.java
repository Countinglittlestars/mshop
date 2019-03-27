package com.skymall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Attribute;
import com.skymall.domain.AttributeCategory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface IAttributeCategoryService extends IService<AttributeCategory> {
    IPage<AttributeCategory> queryByPage(Integer page,Integer size, AttributeCategory attributeCategory);

    List<AttributeCategory> queryEnableEnabledAttrCate();
}
