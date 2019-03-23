package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.FootprintMapper;
import com.skymall.domain.Footprint;
import com.skymall.service.IWcFootPrintService;
import com.skymall.vo.wechat.FootprintVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WcFootPrintService extends ServiceImpl<FootprintMapper, Footprint> implements IWcFootPrintService {

    @Resource
    FootprintMapper footprintMapper;

    /**
     * 根据用户ID分页查询所有足迹
     * @param page
     * @param userId
     * @return
     */
    public IPage<FootprintVo> queryByPage(Page page, Integer userId){
        IPage page1 = footprintMapper.queryByPage(page, userId);
        return page1;
    }
}
