package com.skymall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.domain.GoodsSpecification;
import com.skymall.dto.GoodSpecificationQueryDto;
import com.skymall.dto.GoodsSpecQueryDto;
import com.skymall.vo.GoodsSpecificationWithName;
import com.skymall.vo.admin.GoodSpecificationEntity;
import com.skymall.vo.admin.GoodsSpecificationEntity;
import com.skymall.vo.wechat.GoodsSpecificationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品对应规格表值表 Mapper 接口
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface GoodsSpecificationMapper extends BaseMapper<GoodsSpecification> {

    List<GoodsSpecificationWithName> getSpecificationWithName(Integer goodsId);

    List<GoodsSpecificationVo> queryList(Map map);

    List<GoodsSpecificationEntity> queryEntityList(GoodSpecificationQueryDto goodSpecificationQueryDto );

    IPage<GoodSpecificationEntity> queryAll(Page page);

    List<GoodSpecificationEntity> queryById(Integer id);

    IPage<GoodSpecificationEntity> queryByExample
            (Page page,@Param(value = "dto") GoodsSpecQueryDto goodsSpecQueryDto);
}
