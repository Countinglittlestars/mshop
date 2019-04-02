package com.skymall.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.dto.AddressQueryDto;
import com.skymall.vo.admin.AddressEntity;
import com.skymall.vo.wechat.AddressVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
public interface AddressMapper extends BaseMapper<Address> {
    IPage queryByAddressQueryDto
            (Page page, @Param(value = "dto") AddressQueryDto addressQueryDto);

    List<AddressVo> queryList(Map map);


}
