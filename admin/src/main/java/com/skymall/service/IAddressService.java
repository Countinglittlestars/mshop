package com.skymall.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Address;
import com.skymall.dto.AddressAddDto;
import com.skymall.dto.AddressQueryDto;
import com.skymall.vo.admin.AddressEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface IAddressService extends IService<Address> {
    IPage<Address> queryByPage(Page<Address> page);

    IPage<AddressEntity> pageByAddressQueryDto(Integer page, Integer size, AddressQueryDto addressQueryDto);

    Object addAddress(AddressAddDto addressAddDto);

//    Object queryAddress(AddressEntity addressEntity,Integer page, Integer size);


}
