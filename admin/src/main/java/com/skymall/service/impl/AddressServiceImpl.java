package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AddressMapper;
import com.skymall.domain.Address;
import com.skymall.dto.AddressAddDto;
import com.skymall.dto.AddressQueryDto;
import com.skymall.service.IAddressService;
import com.skymall.utils.BeanUtils;
import com.skymall.vo.CommonResult;
import com.skymall.vo.admin.AddressEntity;
import io.swagger.models.auth.In;
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
@Service("IAddressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
    @Resource
    private AddressMapper addressMapper;


    @Override
    public IPage<Address> queryByPage(Page<Address> page) {
        return addressMapper.selectPage(page,null);
    }


    @Override
    public IPage<AddressEntity> pageByAddressQueryDto(Integer page, Integer size, AddressQueryDto addressQueryDto) {
        Page<AddressEntity> addressEntityPage = new Page<>(page,size);
        return addressMapper.queryByAddressQueryDto(addressEntityPage,addressQueryDto);
    }

    @Override
    public Object addAddress(AddressAddDto addressAddDto) {
        Address address = new Address();
        BeanUtils.mapping(addressAddDto,address);
        return addressMapper.insert(address);
    }

//    @Override
//    public Object queryAddress(AddressEntity addressEntity, Integer page, Integer size) {
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        if(null != addressEntity.getUserId()){
//            queryWrapper.eq("user_id",addressEntity.getUserId());
//        }
//        if(null != addressEntity.getUserName()){
//            queryWrapper.eq("user_name",addressEntity.getUserName());
//        }
//        if(null != addressEntity.getTelNumber()){
//            queryWrapper.eq("tel_number",addressEntity.getTelNumber());
//        }
//        if(null != addressEntity.getPostalCode()){
//            queryWrapper.eq("postal_Code",addressEntity.getPostalCode());
//        }
//        if(null != addressEntity.getNationalCode()){
//            queryWrapper.eq("national_Code",addressEntity.getNationalCode());
//        }
//        if(null != addressEntity.getProvinceName()){
//            queryWrapper.eq("province_Name",addressEntity.getProvinceName());
//        }
//        if(null != addressEntity.getCityName()){
//            queryWrapper.eq("city_Name",addressEntity.getCityName());
//        }
//        if(null != addressEntity.getCountyName()){
//            queryWrapper.eq("county_Name",addressEntity.getCountyName());
//        }
//        if(null != addressEntity.getDetailInfo()){
//            queryWrapper.eq("detail_Info",addressEntity.getDetailInfo());
//        }
//        if(null != addressEntity.getIsDefault()){
//            queryWrapper.eq("is_default",addressEntity.getIsDefault());
//        }
//        Page page1 = new Page(page,size);
//        IPage<AddressEntity> data = addressMapper.selectPage(page1,queryWrapper);
//        return data;
//    }


}
