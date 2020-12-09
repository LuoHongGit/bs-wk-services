package com.wk.manage_course.dao;

import com.wk.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachplanRepository extends JpaRepository<Teachplan, String> {
    //根据课程id和父节点id查询课程计划
    Teachplan findByCourseidAndParentid(String courseid, String parentid);
}
