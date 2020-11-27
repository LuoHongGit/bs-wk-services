package com.wk.api.category;

import com.wk.framework.domain.course.ext.CategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "课程分类接口",description = "提供课程分类的增删改查")
public interface CategoryControllerApi {
    @ApiOperation("查询所有课程分类")
    public CategoryNode findCategoryList();
}
