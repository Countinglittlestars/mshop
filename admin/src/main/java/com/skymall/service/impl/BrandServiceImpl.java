package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.BrandMapper;
import com.skymall.domain.Brand;
import com.skymall.service.IBrandService;
import com.skymall.utils.WrapperUtil;
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
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    @Resource
    private BrandMapper brandMapper;
//
//    @Override
//    public List<Brand> selectAll(Brand brand) {
//        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
//        WrapperUtil.wrap(queryWrapper,brand);
//        List<Brand> list = brandMapper.selectList(queryWrapper);
//        return list;
//    }
//
    @Override
    public IPage<Brand> queryByPage(Page<Brand> page) {
        return brandMapper.selectPage(page,null);
    }
}
