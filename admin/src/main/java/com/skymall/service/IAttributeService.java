package com.skymall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Attribute;
import com.skymall.dto.AttributeQueryDto;
import com.skymall.vo.admin.AttributeEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface IAttributeService extends IService<Attribute> {
    List<Attribute> queryByGoodId(Integer goodId);

    IPage<AttributeEntity> queryEntity(Integer page, Integer size, AttributeQueryDto attributeQueryDto);
}
