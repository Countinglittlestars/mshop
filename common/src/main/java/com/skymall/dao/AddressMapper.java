package com.skymall.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.skymall.domain.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.dto.AddressQueryDto;
import com.skymall.vo.admin.AddressEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
public interface AddressMapper extends BaseMapper<Address> {
    List<AddressEntity> queryByAddressQueryDto
            (IPage page, @Param(value = "dto") AddressQueryDto addressQueryDto);
}
