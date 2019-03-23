package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AttributeMapper;
import com.skymall.domain.Attribute;
import com.skymall.service.IWcAttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WcAttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements IWcAttributeService {

    @Resource
    AttributeMapper attributeMapper;

    /**
     * 根据商品id查找所有的参数
     * @param goodId
     * @return
     */
    @Override
    public List<Attribute> queryByGoodId(Integer goodId) {
        List<Attribute> attributes = attributeMapper.queryByGoodId(goodId);
        return  attributes;
    }
}
