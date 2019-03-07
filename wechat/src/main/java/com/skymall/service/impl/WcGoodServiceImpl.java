package com.skymall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.skymall.dao.GoodsMapper;
import com.skymall.domain.Category;
import com.skymall.domain.Goods;
import com.skymall.service.IWcCatagoryService;
import com.skymall.service.IWcGoodService;
import com.skymall.utils.ApiPageUtils;
import com.skymall.utils.WrapperUtil;
import com.skymall.web.dto.requestDto.GoodQueryReqDto;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
@Service
public class WcGoodServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IWcGoodService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private IWcCatagoryService wcCatagoryService;

    @Override
    public List<Goods> selectNewGoods() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Goods::getIsNew, 1);
        return list(queryWrapper);


    }

    @Override
    public List<Goods> selectHotGoods() {
        QueryWrapper entityWrapper = new QueryWrapper();
        entityWrapper.eq("is_hot",1);
        entityWrapper.eq("is_delete", 0);
        PageHelper.startPage(0, 3, false);
        List<Goods> hotGoods = goodsMapper.selectList(entityWrapper);
        return hotGoods;
    }

    @Override
    public ApiPageUtils queryAll(GoodQueryReqDto goodQueryReqDto) {
        goodQueryReqDto.setIsOnSale(1);
        goodQueryReqDto.setIsDelete(0);
        QueryWrapper entityWrapper = new QueryWrapper();
        Page<Goods> page = new Page<>(goodQueryReqDto.getPage(), goodQueryReqDto.getSize());
        WrapperUtil.wrap(entityWrapper, goodQueryReqDto);
        entityWrapper.orderByAsc("id");

        List<Goods> goodsList = goodsMapper.selectPage(page, entityWrapper).getRecords();
        Category currentCategory = wcCatagoryService.selectById(goodQueryReqDto.getCategoryId());

        //包装返回的数据
        ApiPageUtils goodsData = new ApiPageUtils();
        goodsData.setCount(goodsList.size());
        goodsData.setCurrentPage(goodQueryReqDto.getPage());
        goodsData.setGoodsList(goodsList);
        goodsData.setFilterCategory(currentCategory);
        goodsData.setNumsPerPage(goodQueryReqDto.getSize());
        goodsData.setTotalPages((int) Math.ceil((double) goodsList.size() / goodQueryReqDto.getSize()));
        return goodsData;
    }

    @Override
    public Goods queryById(Integer id) {
        Goods goods = goodsMapper.selectById(id);
        return goods;
    }


}
