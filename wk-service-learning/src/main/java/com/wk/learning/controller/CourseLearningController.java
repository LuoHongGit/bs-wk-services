package com.wk.learning.controller;

import com.wk.api.learining.CourseLearningControllerApi;
import com.wk.framework.domain.learning.response.GetMediaResult;
import com.wk.learning.service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/learning/course")
public class CourseLearningController implements CourseLearningControllerApi {
    @Autowired
    LearningService learningService;

    @Override
    @GetMapping("/getmedia/{courseId}/{teachplanId}")
    public GetMediaResult getmedia(@PathVariable("courseId") String courseId, @PathVariable("teachplanId") String teachplanId) {
        //获取课程学习地址
        return learningService.getMedia(courseId, teachplanId);
    }
}