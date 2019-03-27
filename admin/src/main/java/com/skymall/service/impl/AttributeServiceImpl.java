package com.skymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.AttributeMapper;
import com.skymall.domain.Attribute;
import com.skymall.domain.GoodsAttribute;
import com.skymall.dto.AttributeQueryDto;
import com.skymall.service.IAttributeService;
import com.skymall.service.IGoodsAttributeService;
import com.skymall.vo.admin.AttributeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service("IAttributeService")
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements IAttributeService {

    @Resource
    IAttributeService attributeService;

    @Resource
    IGoodsAttributeService goodsAttributeService;

    @Resource
    AttributeMapper attributeMapper;


    @Override
    public List<Attribute> queryByGoodId(Integer goodId) {
        List<Attribute> attributes = new ArrayList<>();
        List<GoodsAttribute> goodsAttributes = goodsAttributeService.list(new QueryWrapper<GoodsAttribute>().lambda().eq(GoodsAttribute::getGoodsId, goodId));
        if(goodsAttributes == null || goodsAttributes.isEmpty()){
            return attributes;
        }
        Map<Integer, String> map = new HashMap<>();
        goodsAttributes.forEach(e -> {
            Attribute attribute = attributeService.getOne(new QueryWrapper<Attribute>().lambda().eq(Attribute::getId, e.getAttributeId()));
            if(attribute != null){
                attribute.setValue(e.getValue());
                attributes.add(attribute);
            }
        });
        return attributes;
    }

    @Override
    public IPage<AttributeEntity> queryEntity(Integer page, Integer size, AttributeQueryDto attributeQueryDto) {
        Page<AttributeEntity> page1 = new Page<>(page,size);
        return attributeMapper.queryEntity(page1,attributeQueryDto);
    }
}
