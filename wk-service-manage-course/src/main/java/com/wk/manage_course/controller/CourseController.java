package com.wk.manage_course.controller;

import com.wk.api.course.CourseControllerApi;
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
import com.wk.framework.exception.ExceptionCast;
import com.wk.framework.model.response.CommonCode;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.framework.model.response.ResponseResult;
import com.wk.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {
    @Autowired
    private CourseService courseService;

    /**
     * 查询课程计划
     */
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachplanList(courseId);
    }

    /**
     * 添加课程计划
     * @param teachplan
     * @return
     */
    @PostMapping("/teachplan/add")
    public ResponseResult addTeachplan(@RequestBody Teachplan teachplan) {
        return courseService.addTeachplan(teachplan);
    }

    /**
     * 分页查询课程列表
     * @param page
     * @param size
     * @param courseListRequest
     * @return
     */
    @GetMapping("/coursebase/list/{page}/{size}")
    public QueryResponseResult<CourseInfo> findCourseListPage(@PathVariable("page") int page,@PathVariable("size") int size, CourseListRequest courseListRequest) {
        return courseService.findCourseListPage(page, size, courseListRequest);
    }

    /**
     * 新增课程
     * @param courseBase
     * @return
     */
    @PostMapping("/coursebase/add")
    public AddCourseResult addCourseBase(@RequestBody CourseBase courseBase) {
        return courseService.addCourseBase(courseBase);
    }

    /**
     * 课程页面发布
     * @param id
     * @return
     */
    @PostMapping("/publish/{id}")
    public CoursePublishResult publish(@PathVariable String id) {
        //return courseService.publish(id);
        return null;
    }

    /**
     * 通过id查询课程基本信息
     * @param courseid
     * @return
     */
    @GetMapping("/coursebase/findById/{courseid}")
    public CourseBase findCourseBaseById(@PathVariable("courseid") String courseid){
        return courseService.findCourseBaseById(courseid);
    }

    /**
     * 更新课程基本信息
     * @param courseid
     * @param courseBase
     * @return
     */
    @PostMapping("/coursebase/update/{courseid}")
    public ResponseResult updateCourseBase(@PathVariable("courseid") String courseid, @RequestBody CourseBase courseBase) {
        return courseService.updateCourseBase(courseid, courseBase);
    }

    /**
     * 更新课程营销信息
     * @param courseid
     * @param courseMarket
     * @return
     */
    @PostMapping("/courseMarket/update/{courseid}")
    public ResponseResult updateCourseBase(@PathVariable("courseid") String courseid, @RequestBody CourseMarket courseMarket) {
        CourseMarket one = courseService.updateCourseMarket(courseid, courseMarket);

        if (one == null) {
            return new ResponseResult(CommonCode.FAIL);
        }

        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 通过id查询课程营销信息
     * @param courseid
     * @return
     */
    @GetMapping("/courseMarket/get/{courseid}")
    public CourseMarket findCourseMarketById(@PathVariable("courseid") String courseid){
        return courseService.findCourseMarketById(courseid);
    }

    /**
     * 添加课程图片
     * @param courseId
     * @param pic
     * @return
     */
    @PostMapping("/coursepic/add")
    public ResponseResult addCoursePic(@RequestParam("courseId") String courseId,@RequestParam("pic") String pic) {
        return courseService.addCoursePic(courseId, pic);
    }

    @GetMapping("/coursepic/list/{courseId}")
    public CoursePic findCoursePicById(@PathVariable("courseId")String courseId) {
        return courseService.findCoursePicById(courseId);
    }

    @DeleteMapping("/coursepic/delete")
    public ResponseResult deleteCoursePicById(@RequestParam("courseId") String courseId) {
        return courseService.deleteCoursePicById(courseId);
    }

    @GetMapping("/courseview/{id}")
    public CourseView courseview(@PathVariable("id") String id) {
        return courseService.getCoruseView(id);
    }

    @PostMapping("/preview/{id}")
    public CoursePublishResult coursePreview(@PathVariable("id")String id) {
        //return courseService.preview(id);
        return null;
    }

}
