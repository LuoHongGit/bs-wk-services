package com.wk.manage_course.dao;

import com.wk.framework.domain.course.CoursePic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePicRepository extends JpaRepository<CoursePic, String> {
    long deleteByCourseid(String courseid);
}
