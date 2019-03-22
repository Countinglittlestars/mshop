package com.skymall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skymall.domain.Goods;
import com.skymall.dto.GoodAddDto;
import com.skymall.dto.GoodQueryDto;
import com.skymall.dto.GoodUpdateInfoDto;

public interface IGoodService extends IService<Goods> {

    public Object queryByPage(GoodQueryDto goodQueryDto, Integer pageNum, Integer pageSize);

    public Object addGood(GoodAddDto goodAddDto);

    public GoodUpdateInfoDto querySelect(Integer id);

}
