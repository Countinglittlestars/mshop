package com.skymall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skymall.dao.ProductMapper;
import com.skymall.domain.Product;
import com.skymall.service.IWcProductService;
import com.skymall.vo.wechat.ProductVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WcProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IWcProductService {

    @Resource
    ProductMapper productMapper;

    /**
     * 根据商品id查看产品信息，不包括图片信息
     * @return
     */
    public List<ProductVo> queryList(Integer productId){
        List<ProductVo> productVos = productMapper.queryList(productId);
        return productVos;
    }

    @Override
    public ProductVo queryObject(Integer productId) {
        return null;
    }

}
