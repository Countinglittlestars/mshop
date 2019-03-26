package com.skymall.vo.admin;

import lombok.Data;

import java.util.Date;

@Data
public class CollectEntity {
    private Integer id;
    private String userId;
    private String userName;
    private String goodsName;
    private String goodsSn;
    private Date createTime;
    private Boolean isOnSale;
    private String ListPicUrl;



}
