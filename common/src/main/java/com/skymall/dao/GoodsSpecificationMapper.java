package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.domain.GoodsSpecification;
import com.skymall.vo.GoodsSpecificationWithName;

import java.util.List;

/**
 * <p>
 * 商品对应规格表值表 Mapper 接口
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface GoodsSpecificationMapper extends BaseMapper<GoodsSpecification> {

    List<GoodsSpecificationWithName> getSpecificationWithName(Integer goodsId);
}
