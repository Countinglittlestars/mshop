package com.skymall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.GoodsSpecification;
import com.skymall.dto.GoodSpecificationQueryDto;
import com.skymall.dto.GoodsSpecQueryDto;
import com.skymall.vo.admin.GoodSpecificationEntity;
import com.skymall.vo.admin.GoodsSpecificationEntity;
import org.apache.ibatis.annotations.Param;

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

    IPage<GoodSpecificationEntity> queryAll(Page page);

    List<GoodSpecificationEntity> queryById(Integer id);

    IPage<GoodSpecificationEntity> queryByExample
            (Page page,GoodsSpecQueryDto goodsSpecQueryDto);


}
