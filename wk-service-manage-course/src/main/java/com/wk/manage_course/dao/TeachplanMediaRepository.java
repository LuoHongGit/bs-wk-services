package com.wk.manage_course.dao;

import com.wk.framework.domain.course.TeachplanMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeachplanMediaRepository extends JpaRepository<TeachplanMedia,String> {

    List<TeachplanMedia> findByCourseId(String courseId);
}
