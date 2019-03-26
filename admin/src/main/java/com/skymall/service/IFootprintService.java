package com.skymall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Footprint;
import com.skymall.dto.FootPrintQueryDto;
import com.skymall.vo.wechat.FootprintVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface IFootprintService extends IService<Footprint> {
    public IPage<FootprintVo> queryByPage(Page<FootprintVo> page, FootPrintQueryDto footPrintQueryDto);


}
