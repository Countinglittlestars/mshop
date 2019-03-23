package com.skymall.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.domain.Product;
import com.skymall.vo.wechat.ProductVo;

import java.util.List;

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


}
