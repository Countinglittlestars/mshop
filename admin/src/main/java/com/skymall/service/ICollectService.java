package com.skymall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Collect;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface ICollectService extends IService<Collect> {
    IPage<Collect> queryByPage(Page<Collect> page);
    IPage<Collect> pageByExample(Page<Collect> page, QueryWrapper<Collect> queryWrapper);
}
