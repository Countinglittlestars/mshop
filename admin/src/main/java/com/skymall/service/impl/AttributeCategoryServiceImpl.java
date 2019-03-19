package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AttributeCategoryMapper;
import com.skymall.domain.AttributeCategory;
import com.skymall.service.IAttributeCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
//    @Resource
//    private AttributeCategoryMapper attributeCategoryMapper;
}
