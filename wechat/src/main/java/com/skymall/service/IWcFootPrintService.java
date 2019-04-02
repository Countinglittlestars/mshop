package com.skymall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Footprint;
import com.skymall.vo.wechat.FootprintVo;

import java.util.List;

public interface IWcFootPrintService extends IService<Footprint> {
    public IPage<FootprintVo> queryByPage(Page page, Integer userId);

    public List<FootprintVo> queryListFootprint(String userid) ;
}
