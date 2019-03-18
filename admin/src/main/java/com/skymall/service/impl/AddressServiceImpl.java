package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AddressMapper;
import com.skymall.domain.Address;
import com.skymall.service.IAddressService;
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
@Service("IAddressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
    @Resource
    private AddressMapper addressMapper;


    @Override
    public IPage<Address> queryByPage(Page<Address> page) {
        return addressMapper.selectPage(page,null);
    }

    @Override
    public IPage<Address> pageByCondition(Page<Address> page, QueryWrapper<Address> queryWrapper) {
        return addressMapper.selectPage(page,queryWrapper);
    }
}
