package com.skymall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.BrandMapper;
import com.skymall.domain.Brand;
import com.skymall.dto.BrandAddDto;
import com.skymall.dto.BrandQueryDto;
import com.skymall.service.IBrandService;

import com.skymall.utils.BeanUtils;
import com.skymall.vo.CommonResult;
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
    public BrandQueryDto queryBrandById(Integer id) {
        Brand brand = brandMapper.selectById(id);
        BrandQueryDto brandQueryDto = new BrandQueryDto();
        BeanUtils.mapping(brand,brandQueryDto);
        return brandQueryDto;
    }

    @Override
    public Object queryBrandByPage(BrandQueryDto brandQueryDto,Integer page,Integer size) {
        Page<Brand> brandPage = new Page<>(page,size);
        LambdaQueryWrapper<Brand> wrapper = new QueryWrapper<Brand>().lambda().orderByAsc(Brand::getSortOrder);

        if(brandQueryDto.getName() != null) {
            wrapper = wrapper.like(Brand::getName, brandQueryDto.getName());
        }
        if(brandQueryDto.getSimpleDesc() != null) {
            wrapper = wrapper.like(Brand::getSimpleDesc, brandQueryDto.getSimpleDesc());
        }

        IPage<Brand> data = brandMapper.selectPage(brandPage,wrapper);
        return data;
    }

}
