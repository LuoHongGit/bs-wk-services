package com.wk.manage_course.service;

import com.wk.framework.domain.course.ext.CategoryNode;
import com.wk.manage_course.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询所有课程分类
     * @return
     */
    public CategoryNode findCategoryList(){
        return categoryMapper.findCategoryList();
    }
}
