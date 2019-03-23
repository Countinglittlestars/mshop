package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.GoodsSpecificationMapper;
import com.skymall.domain.GoodsSpecification;
import com.skymall.service.IWcGoodSpecificationService;
import com.skymall.vo.GoodsSpecificationWithName;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class WcGoodSpecificationServiceImpl extends ServiceImpl<GoodsSpecificationMapper, GoodsSpecification> implements IWcGoodSpecificationService {


    @Resource
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<GoodsSpecificationWithName> getSpecificationWithName(Integer goodId) {
        return goodsSpecificationMapper.getSpecificationWithName(goodId);
    }
}