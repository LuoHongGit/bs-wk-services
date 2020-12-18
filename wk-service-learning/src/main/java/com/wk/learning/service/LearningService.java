package com.wk.learning.service;

import com.wk.framework.domain.course.TeachplanMediaPub;
import com.wk.framework.domain.learning.response.GetMediaResult;
import com.wk.framework.exception.ExceptionCast;
import com.wk.framework.model.response.CommonCode;
import com.wk.framework.model.response.LearningCode;
import com.wk.learning.client.CourseSearchClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearningService {
    @Autowired
    CourseSearchClient courseSearchClient;

    /**
     * 获取课程计划对应的媒体资源信息
     * @param courseId
     * @param teachplanId
     * @return
     */
    public GetMediaResult getMedia(String courseId, String teachplanId) {
        //校验学生的学习权限。。是否资费等
        //调用搜索服务查询
        TeachplanMediaPub teachplanMediaPub = courseSearchClient.getmedia(teachplanId);
        if (teachplanMediaPub == null || StringUtils.isEmpty(teachplanMediaPub.getMediaUrl())) {
            //获取视频播放地址出错
            ExceptionCast.cast(LearningCode.LEARNING_GETMEDIA_ERROR);
        }
        return new GetMediaResult(CommonCode.SUCCESS, teachplanMediaPub.getMediaUrl());
    }
}