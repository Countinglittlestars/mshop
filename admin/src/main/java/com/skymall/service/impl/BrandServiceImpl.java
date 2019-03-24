package com.skymall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.BrandMapper;
import com.skymall.domain.Brand;
import com.skymall.dto.BrandAddDto;
import com.skymall.dto.BrandQueryDto;
import com.skymall.enums.ExceptionEnums;
import com.skymall.service.IBrandService;

import com.skymall.utils.BeanUtils;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
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
        QueryWrapper queryWrapper = new QueryWrapper();
        Page<Brand> brandPage = new Page<>(page,size);
        if(brandQueryDto.getName() != null) {
            queryWrapper.like("name", brandQueryDto.getName());
        }
        if(brandQueryDto.getSimpleDesc() != null) {
            queryWrapper.eq("is_on_sale", brandQueryDto.getSimpleDesc());
        }
        if(brandQueryDto.getIsNew() != null) {
            queryWrapper.eq("brand_id", brandQueryDto.getIsNew());
        }
        if(brandQueryDto.getIsShow() != null) {
            queryWrapper.eq("goods_sn", brandQueryDto.getIsShow());
        }
        if(brandQueryDto.getSortOrder() != null) {
            queryWrapper.eq("is_hot", brandQueryDto.getSortOrder());
        }
        if(brandQueryDto.getNewSortOrder() != null) {
            queryWrapper.eq("is_new", brandQueryDto.getNewSortOrder());
        }
        IPage<Brand> data = brandMapper.selectPage(brandPage,queryWrapper);
        return data;
    }
}
