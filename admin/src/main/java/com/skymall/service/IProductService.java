package com.skymall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Product;
import com.skymall.dto.ProductQueryDto;
import com.skymall.vo.admin.ProductEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaogengren123
 * @since 2019-03-04
 */
public interface IProductService extends IService<Product> {
    IPage<ProductEntity> queryList(IPage page, ProductQueryDto productQueryDto);
}
