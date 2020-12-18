package com.wk.api.search;

import com.wk.framework.domain.course.CoursePub;
import com.wk.framework.domain.course.TeachplanMediaPub;
import com.wk.framework.domain.search.CourseSearchParam;
import com.wk.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Api("课程搜索接口")
public interface EsCourseControllerApi {
    @ApiOperation("分页条件查询课程列表")
    QueryResponseResult<CoursePub> list(int page, int size, CourseSearchParam courseSearchParam);

    @ApiOperation("根据id查询课程信息")
    Map<String,CoursePub> getall(String id);

    @ApiOperation("根据课程计划查询媒资信息")
    TeachplanMediaPub getmedia(String teachplanId);
}
