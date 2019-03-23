package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Attribute;

import java.util.List;

public interface IWcAttributeService extends IService<Attribute> {

    List<Attribute> queryByGoodId(Integer goodId);

}
