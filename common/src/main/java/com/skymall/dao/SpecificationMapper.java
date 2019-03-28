package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.Specification;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 规格表 Mapper 接口
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface SpecificationMapper extends BaseMapper<Specification> {
    IPage querySpec(Page page,@Param(value = "dto") Specification specification);
}
