package com.skymall.dao;

import com.skymall.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skymall.domain.CategoryWithChildrenItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
public interface CategoryMapper extends BaseMapper<Category> {
//    @Select("select c1.id, c1.name, c2.id child_id, c2.name child_name from nideshop_category c1 LEFT JOIN nideshop_category c2 on c1.id = c2.parent_id")
    public List<CategoryWithChildrenItem> listWithChildren();

}
