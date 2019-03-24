package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.GoodsSpecification;
import com.skymall.dto.GoodSpecificationQueryDto;
import com.skymall.vo.admin.GoodsSpecificationEntity;

import java.util.List;

/**
 * <p>
 * 商品对应规格表值表 服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface IGoodsSpecificationService extends IService<GoodsSpecification> {

    List<GoodsSpecificationEntity> queryList(GoodSpecificationQueryDto goodSpecificationQueryDto);


}
