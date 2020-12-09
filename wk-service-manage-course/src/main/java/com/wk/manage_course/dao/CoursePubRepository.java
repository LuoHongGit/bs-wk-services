package com.wk.manage_course.dao;

import com.wk.framework.domain.course.CoursePub;
import com.wk.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePubRepository extends JpaRepository<CoursePub, String> {
}
