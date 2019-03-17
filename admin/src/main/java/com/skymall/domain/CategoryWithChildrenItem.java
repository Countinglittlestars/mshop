package com.skymall.domain;

import java.util.List;

public class CategoryWithChildrenItem extends Category {

    private List<Category> children;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
