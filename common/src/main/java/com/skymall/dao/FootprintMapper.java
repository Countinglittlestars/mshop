package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.Footprint;
import com.skymall.dto.FootPrintQueryDto;
import com.skymall.vo.wechat.FootprintVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface FootprintMapper extends BaseMapper<Footprint> {

    IPage<FootprintVo> queryByPage(Page page, @Param("userId")Integer userId);

    IPage<FootprintVo> queryPage(IPage page, FootPrintQueryDto dto);
}
