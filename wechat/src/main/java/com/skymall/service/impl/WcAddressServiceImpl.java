package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AddressMapper;
import com.skymall.domain.Address;
import com.skymall.service.IWcAddressService;
import com.skymall.vo.wechat.AddressVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class WcAddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IWcAddressService {
    @Resource
    AddressMapper addressMapper;

//    @Override
//    public List<AddressVo> queryList(Map map) {
//        return null;
//    }
}
