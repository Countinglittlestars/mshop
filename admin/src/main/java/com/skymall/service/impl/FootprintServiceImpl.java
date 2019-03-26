package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.FootprintMapper;
import com.skymall.domain.Footprint;
import com.skymall.dto.FootPrintQueryDto;
import com.skymall.service.IFootprintService;
import com.skymall.vo.wechat.FootprintVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service("IFootprintService")
public class FootprintServiceImpl extends ServiceImpl<FootprintMapper, Footprint> implements IFootprintService {
    @Resource
    private FootprintMapper footprintMapper;
    @Override
    public IPage<FootprintVo> queryByPage(Page<FootprintVo> page, FootPrintQueryDto footPrintQueryDto) {
        return footprintMapper.queryPage(page, footPrintQueryDto);
    }
}
