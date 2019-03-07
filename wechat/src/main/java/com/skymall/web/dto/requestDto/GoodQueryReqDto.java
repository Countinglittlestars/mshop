package com.skymall.web.dto.requestDto;

import com.skymall.vo.AbstractRequestDto;

import java.math.BigDecimal;
import java.util.Date;

public class GoodQueryReqDto extends AbstractRequestDto {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer categoryId;
    private String goodsSn;
    private String name;
    private Integer brandId;
    private Integer goodsNumber;
    private String keywords;
    private String goodsBrief;
    private String goodsDesc;
    private Integer isOnSale;
    private Date addTime;
    private Integer sortOrder;
    private Integer isDelete;
    private Integer attributeCategory;

    private BigDecimal counterPrice;
    /**
     * 附加价格
     */
    private BigDecimal extraPrice;
    private Integer isNew;
    /**
     * 商品单位
     */
    private String goodsUnit;
    /**
     * 商品主图
     */
    private String primaryPicUrl;
    /**
     * 商品列表图
     */
    private String listPicUrl;
    /**
     * 零售价格
     */
    private BigDecimal retailPrice;
    /**
     * 销售量
     */
    private Integer sellVolume;
    /**
     * 主sku　product_id
     */
    private Integer primaryProductId;
    /**
     * 单位价格，单价
     */
    private BigDecimal unitPrice;
    private String promotionDesc;
    private String promotionTag;
    /**
     * APP专享价
     */
    private BigDecimal appExclusivePrice;
    /**
     * 是否是APP专属
     */
    private Integer isAppExclusive;
    private Integer isLimited;
    private Integer isHot;
    private BigDecimal marketPrice;
    /**
     * 创建人ID
     */
    private Long createUserId;
    /**
     * 修改人ID
     */
    private Long updateUserId;
    /**
     * 修改时间
     */
    private Date updateTime;
    private Long createUserDeptId;

    @Override
    public String toString() {
        return "GoodQueryReqDto{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", goodsSn='" + goodsSn + '\'' +
                ", name='" + name + '\'' +
                ", brandId=" + brandId +
                ", goodsNumber=" + goodsNumber +
                ", keywords='" + keywords + '\'' +
                ", goodsBrief='" + goodsBrief + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", isOnSale=" + isOnSale +
                ", addTime=" + addTime +
                ", sortOrder=" + sortOrder +
                ", isDelete=" + isDelete +
                ", attributeCategory=" + attributeCategory +
                ", counterPrice=" + counterPrice +
                ", extraPrice=" + extraPrice +
                ", isNew=" + isNew +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", primaryPicUrl='" + primaryPicUrl + '\'' +
                ", listPicUrl='" + listPicUrl + '\'' +
                ", retailPrice=" + retailPrice +
                ", sellVolume=" + sellVolume +
                ", primaryProductId=" + primaryProductId +
                ", unitPrice=" + unitPrice +
                ", promotionDesc='" + promotionDesc + '\'' +
                ", promotionTag='" + promotionTag + '\'' +
                ", appExclusivePrice=" + appExclusivePrice +
                ", isAppExclusive=" + isAppExclusive +
                ", isLimited=" + isLimited +
                ", isHot=" + isHot +
                ", marketPrice=" + marketPrice +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", updateTime=" + updateTime +
                ", createUserDeptId=" + createUserDeptId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Integer isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(Integer attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    public BigDecimal getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(BigDecimal extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getPrimaryPicUrl() {
        return primaryPicUrl;
    }

    public void setPrimaryPicUrl(String primaryPicUrl) {
        this.primaryPicUrl = primaryPicUrl;
    }

    public String getListPicUrl() {
        return listPicUrl;
    }

    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Integer sellVolume) {
        this.sellVolume = sellVolume;
    }

    public Integer getPrimaryProductId() {
        return primaryProductId;
    }

    public void setPrimaryProductId(Integer primaryProductId) {
        this.primaryProductId = primaryProductId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc;
    }

    public String getPromotionTag() {
        return promotionTag;
    }

    public void setPromotionTag(String promotionTag) {
        this.promotionTag = promotionTag;
    }

    public BigDecimal getAppExclusivePrice() {
        return appExclusivePrice;
    }

    public void setAppExclusivePrice(BigDecimal appExclusivePrice) {
        this.appExclusivePrice = appExclusivePrice;
    }

    public Integer getIsAppExclusive() {
        return isAppExclusive;
    }

    public void setIsAppExclusive(Integer isAppExclusive) {
        this.isAppExclusive = isAppExclusive;
    }

    public Integer getIsLimited() {
        return isLimited;
    }

    public void setIsLimited(Integer isLimited) {
        this.isLimited = isLimited;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserDeptId() {
        return createUserDeptId;
    }

    public void setCreateUserDeptId(Long createUserDeptId) {
        this.createUserDeptId = createUserDeptId;
    }
}
