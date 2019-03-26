package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.CollectMapper;
import com.skymall.domain.Collect;
import com.skymall.dto.CollectQueryDto;
import com.skymall.service.ICollectService;
import com.skymall.vo.admin.CollectEntity;
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
    public IPage<CollectEntity> queryByPage(Page<Collect> page, CollectQueryDto dto) {
        return collectMapper.queryPage(page,dto);
    }

    @Override
    public IPage<Collect> pageByExample(Page<Collect> page, QueryWrapper<Collect> queryWrapper) {
        return collectMapper.selectPage(page,queryWrapper);
    }
}
