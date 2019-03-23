package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Collect;
import com.skymall.vo.wechat.CollectVo;

import java.util.List;
import java.util.Map;

public interface IWcCollectService extends IService<Collect> {
    public List<CollectVo> queryListByUserId(Map map);
}
