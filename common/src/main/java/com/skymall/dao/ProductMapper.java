package com.skymall.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.skymall.domain.Product;
import com.skymall.dto.ProductQueryDto;
import com.skymall.vo.admin.ProductEntity;
import com.skymall.vo.wechat.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface ProductMapper extends BaseMapper<Product> {
    List<ProductVo> queryList(Integer goodsId);

    ProductVo queryObject(Integer goodsId);

    IPage<ProductEntity> queryEntityList(IPage page, @Param(value = "dto") ProductQueryDto productQueryDto);

}
