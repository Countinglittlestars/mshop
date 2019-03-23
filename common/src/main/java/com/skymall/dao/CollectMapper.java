package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.domain.Collect;
import com.skymall.vo.wechat.CollectVo;

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
public interface CollectMapper extends BaseMapper<Collect> {
    List<CollectVo> queryListByUserId(Map map);
}
