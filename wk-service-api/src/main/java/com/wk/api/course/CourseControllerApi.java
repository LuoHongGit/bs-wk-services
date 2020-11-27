package com.wk.api.course;

import com.wk.framework.domain.course.CourseBase;
import com.wk.framework.domain.course.CourseMarket;
import com.wk.framework.domain.course.CoursePic;
import com.wk.framework.domain.course.Teachplan;
import com.wk.framework.domain.course.ext.CourseInfo;
import com.wk.framework.domain.course.ext.TeachplanNode;
import com.wk.framework.domain.course.request.CourseListRequest;
import com.wk.framework.domain.course.response.AddCourseResult;
import com.wk.framework.domain.course.response.CoursePublishResult;
import com.wk.framework.domain.course.response.CourseView;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 课程控制层API
 */
@Api(value="课程管理接口",description = "课程管理接口，提供课程的增、删、改、查")
public interface CourseControllerApi {
    //查询课程计划
    @ApiOperation("根据课程id查询课程计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name="courseId",value = "课程id",required=true,paramType="path",dataType="string")
    })
    TeachplanNode findTeachplanList(String courseId);

    //添加课程计划
    @ApiOperation("添加课程计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name="teachplan",value = "课程计划",required=true)
    })
    ResponseResult addTeachplan(Teachplan teachplan);

    //分页查询课程
    @ApiOperation("分页查询课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="courseListRequest",value = "课程查询条件")
    })
    QueryResponseResult<CourseInfo> findCourseListPage(int page, int size, CourseListRequest courseListRequest);

    //新增课程
    @ApiOperation("新增课程")
    AddCourseResult addCourseBase(CourseBase courseBase);

    //通过id查询课程基本信息
    @ApiOperation("通过id查询课程基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseid", value = "课程id", required = true, paramType = "path", dataType = "string")
    })
    CourseBase findCourseBaseById(String courseid);

    //更新课程基本信息
    @ApiOperation("更新课程基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseid", value = "课程id", required = true, paramType = "path", dataType = "string"),
    })
    ResponseResult updateCourseBase(String courseid, CourseBase courseBase);

    @ApiOperation("更新课程营销信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseid", value = "课程id",required = true, paramType = "path", dataType = "string")
    })
    ResponseResult updateCourseBase(String courseid, CourseMarket courseMarket);

    @ApiOperation("通过id查询课程营销信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseid", value = "课程id",required = true, paramType = "path", dataType = "string")
    })
    CourseMarket findCourseMarketById(String courseid);

    @ApiOperation("添加课程图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id",required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pic", value = "文件id",required = true, paramType = "query", dataType = "string")
    })
    ResponseResult addCoursePic(String courseId, String pic);

    @ApiOperation("通过id查询课程图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id",required = true,paramType = "path",dataType = "string")
    })
    CoursePic findCoursePicById(String courseId);

    @ApiOperation("删除课程图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id",required = true,paramType = "path",dataType = "string")
    })
    ResponseResult deleteCoursePicById(String courseId);

    @ApiOperation("课程视图查询")
    CourseView courseview(String id);

    @ApiOperation("课程详情页面预览")
    CoursePublishResult coursePreview(String id);

    @ApiOperation("发布课程")
    CoursePublishResult publish(@PathVariable String id);
}
