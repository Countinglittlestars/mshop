package com.skymall.vo.wechat;

import lombok.Data;

@Data
public class CollectVo {
    //主键
    private Integer id;
    //用户Id
    private Integer userId;
    //产品Id
    private Integer valueId;
    //添加时间
    private Long addTime;
    //是否是关注
    private Integer isAttention;
    //
    private Integer typeId;
    //
    private String name;
    private String listPicUrl;
    private String goodsBrief;
    private String retailPrice;
}
