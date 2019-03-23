package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.domain.Attribute;
import com.skymall.domain.GoodsAttribute;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface GoodsAttributeMapper extends BaseMapper<GoodsAttribute> {
    List<Attribute> selectGoodsToAttribute(Integer goodId);
}
