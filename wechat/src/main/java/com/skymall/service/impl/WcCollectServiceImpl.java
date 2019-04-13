package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.CollectMapper;
import com.skymall.domain.Collect;
import com.skymall.service.IWcCollectService;
import com.skymall.vo.wechat.CollectVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class WcCollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements IWcCollectService {

    @Resource
    CollectMapper collectMapper;

    /**
     * 返回CollectVo信息，如果不输入userId的话则会查询所有信息，包含了商品价格等信息在内的CollectVo信息
     * @param map
     * @return
     */
    public List<CollectVo> queryListByUserId(Map map){
        List<CollectVo> collectVos = collectMapper.queryList(map);
        return collectVos;
    }

    @Override
    public List<CollectVo> queryList(Map map) {
        List<CollectVo> collectVos = collectMapper.queryList(map);
        return collectVos;
    }

}
