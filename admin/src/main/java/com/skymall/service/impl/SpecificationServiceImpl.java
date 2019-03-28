package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.SpecificationMapper;
import com.skymall.domain.Specification;
import com.skymall.service.ISpecificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {
    @Resource
    SpecificationMapper specificationMapper;
    @Override
    public IPage querySpec(Page page, Specification specification) {
        return specificationMapper.querySpec(page,specification);
    }
}
