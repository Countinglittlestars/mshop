package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AddressMapper;
import com.skymall.domain.Address;
import com.skymall.service.IAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
