package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.skymall.domain.Collect;
import com.skymall.dto.CollectQueryDto;
import com.skymall.vo.admin.CollectEntity;
import com.skymall.vo.wechat.CollectVo;
import qiniu.happydns.util.IP;

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

    IPage<CollectEntity> queryPage(IPage page, CollectQueryDto dto);

    List<CollectVo> queryList(Map map);
}
