package com.skymall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Brand;
import com.skymall.dto.BrandAddDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface IBrandService extends IService<Brand> {
    public IPage<Brand> queryByPage(Page<Brand> page);
    public Object addBrand(BrandAddDto brandAddDto);
    public Object queryBrandById(Integer id);

}
