package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.GoodsSpecificationMapper;
import com.skymall.domain.GoodsSpecification;
import com.skymall.dto.GoodSpecificationQueryDto;
import com.skymall.service.IGoodsSpecificationService;
import com.skymall.vo.admin.GoodsSpecificationEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品对应规格表值表 服务实现类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
@Service
public class GoodsSpecificationServiceImpl extends ServiceImpl<GoodsSpecificationMapper, GoodsSpecification> implements IGoodsSpecificationService {

    @Resource
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<GoodsSpecificationEntity> queryList(GoodSpecificationQueryDto goodSpecificationQueryDto) {
        List<GoodsSpecificationEntity> list = goodsSpecificationMapper.queryEntityList(goodSpecificationQueryDto);
        return list;
    }
}
