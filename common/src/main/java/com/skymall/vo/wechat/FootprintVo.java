package com.skymall.vo.wechat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.skymall.utils.JsonDateSerializer;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:40
 */
public class FootprintVo implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Long getAddTime() {
        return addTime;
    }

    public void setAdd_time(Long addTime) {
        this.addTime = addTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListPicUrl() {
        return listPicUrl;
    }

    public void setListPicUrl(String list_pic_url) {
        this.listPicUrl = list_pic_url;
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoods_brief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }


    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retail_price) {
        this.retailPrice = retail_price;
    }

    public Long getReferrer() {
        return referrer;
    }

    public void setReferrer(Long referrer) {
        this.referrer = referrer;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
