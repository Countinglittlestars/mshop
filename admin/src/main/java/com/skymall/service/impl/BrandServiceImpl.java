package com.skymall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.BrandMapper;
import com.skymall.domain.Brand;
import com.skymall.dto.BrandAddDto;
import com.skymall.enums.ExceptionEnums;
import com.skymall.service.IBrandService;

import com.skymall.utils.BeanUtils;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
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
@Service("IBrandService")
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    @Resource
    private BrandMapper brandMapper;


    @Override
    public IPage<Brand> queryByPage(Page<Brand> page) {
        return brandMapper.selectPage(page,null);
    }

    @Override
    public Object addBrand(BrandAddDto brandAddDto) {
        Brand brand = new Brand();
        BeanUtils.mapping(brandAddDto, brand);
        brandMapper.insert(brand);
        return new CommonResult().success(brand.getId());
    }

    @Override
    public Object queryBrandById(Integer id) {
        Brand brand = brandMapper.selectById(id);
        BrandAddDto brandAddDto = new BrandAddDto();
        BeanUtils.mapping(brand,brandAddDto);

        return null;
    }
}
