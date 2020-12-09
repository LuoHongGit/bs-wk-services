package com.wk.manage_course.dao;

import com.github.pagehelper.Page;
import com.wk.framework.domain.course.CourseBase;
import com.wk.framework.domain.course.ext.CourseInfo;
import com.wk.framework.domain.course.request.CourseListRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator.
 */
@Mapper
public interface CourseMapper {
   //通过id查询课程
   CourseBase findCourseBaseById(String id);

   //查询所有课程
   Page<CourseInfo> findCourseListPage(CourseListRequest courseListRequest);
}
