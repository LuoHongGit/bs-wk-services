package com.wk.learning.client;

import com.wk.framework.domain.course.TeachplanMediaPub;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "wk-search-service")
public interface CourseSearchClient {
@GetMapping(value="/search/course/getmedia/{teachplanId}")
public TeachplanMediaPub getmedia(@PathVariable("teachplanId") String teachplanId);
}