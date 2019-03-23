package com.skymall.vo.wechat;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FootprintVo {

    //主键
    private Integer id;
    //会员Id
    private Long userId;
    //商品id
    private Integer goodsId;
    //记录时间
    private Long addTime;
    //推荐人
    private Long referrer;

    // 商品冗余字段
    private String name;
    private String listPicUrl;
    private String goodsBrief;
    //
    private BigDecimal retailPrice;
    // 会员
    private String nickname;
    private String avatar;
}
