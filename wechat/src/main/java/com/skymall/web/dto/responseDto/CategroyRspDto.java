package com.skymall.web.dto.responseDto;

import com.skymall.domain.Category;

import java.util.List;

public class CategroyRspDto {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String keywords;
    private String frontDesc;
    /**
     * 父类型
     */
    private Integer parentId;
    private Integer sortOrder;
    private Integer showIndex;
    private Integer isShow;
    private String bannerUrl;
    private String iconUrl;
    private String imgUrl;
    private String wapBannerUrl;
    private String level;
    private Integer type;
    private String frontName;
    private List<Category> subCategoryList;
    private Boolean check;


    @Override
    public String toString() {
        return "CategroyReqDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keywords='" + keywords + '\'' +
                ", frontDesc='" + frontDesc + '\'' +
                ", parentId=" + parentId +
                ", sortOrder=" + sortOrder +
                ", showIndex=" + showIndex +
                ", isShow=" + isShow +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", wapBannerUrl='" + wapBannerUrl + '\'' +
                ", level='" + level + '\'' +
                ", type=" + type +
                ", frontName='" + frontName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFrontDesc() {
        return frontDesc;
    }

    public void setFrontDesc(String frontDesc) {
        this.frontDesc = frontDesc;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWapBannerUrl() {
        return wapBannerUrl;
    }

    public void setWapBannerUrl(String wapBannerUrl) {
        this.wapBannerUrl = wapBannerUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    public List<Category> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<Category> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
