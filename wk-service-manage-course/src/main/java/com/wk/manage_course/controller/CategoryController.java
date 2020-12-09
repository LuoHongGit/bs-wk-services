package com.wk.manage_course.controller;

import com.wk.framework.domain.course.ext.CategoryNode;
import com.wk.manage_course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public CategoryNode findCategoryList(){
        return categoryService.findCategoryList();
    }
}
