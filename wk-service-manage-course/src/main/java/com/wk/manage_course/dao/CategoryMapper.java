package com.wk.manage_course.dao;

import com.wk.framework.domain.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryNode findCategoryList();
}
