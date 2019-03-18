package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.CollectMapper;
import com.skymall.domain.Collect;
import com.skymall.service.ICollectService;
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
@Service("ICollectService")
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {
    @Resource
    private CollectMapper collectMapper;
    @Override
    public IPage<Collect> queryByPage(Page<Collect> page) {
        return collectMapper.selectPage(page,null);
    }

    @Override
    public IPage<Collect> pageByCondition(Page<Collect> page, QueryWrapper<Collect> queryWrapper) {
        return collectMapper.selectPage(page,queryWrapper);
    }
}
