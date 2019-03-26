package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.Attribute;
import com.skymall.dto.AttributeQueryDto;
import com.skymall.vo.admin.AttributeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface AttributeMapper extends BaseMapper<Attribute> {

     List<Attribute> queryByGoodId(Integer goodId);

     IPage queryEntity
             (Page page, @Param(value = "dto") AttributeQueryDto attributeQueryDto);
}
