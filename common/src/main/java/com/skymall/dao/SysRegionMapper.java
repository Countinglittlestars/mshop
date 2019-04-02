package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.domain.SysRegion;
import com.skymall.vo.wechat.SysRegionEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface SysRegionMapper extends BaseMapper<SysRegion> {
    List<SysRegionEntity> queryList(Map map);
}
