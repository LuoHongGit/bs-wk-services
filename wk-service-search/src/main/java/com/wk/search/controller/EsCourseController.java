package com.wk.search.controller;

import com.wk.api.search.EsCourseControllerApi;
import com.wk.framework.domain.course.CoursePub;
import com.wk.framework.domain.course.TeachplanMediaPub;
import com.wk.framework.domain.search.CourseSearchParam;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.framework.model.response.QueryResult;
import com.wk.search.service.EsCourseService;
import org.elasticsearch.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/search/course")
public class EsCourseController implements EsCourseControllerApi {
    @Autowired
    EsCourseService esCourseService;

    /**
     * 分页查询已发布课程信息
     * @param page
     * @param size
     * @param courseSearchParam
     * @return
     */
    @GetMapping(value="/list/{page}/{size}")
    public QueryResponseResult<CoursePub> list(@PathVariable("page") int page, @PathVariable("size") int size, CourseSearchParam courseSearchParam) {
        return esCourseService.list(page,size,courseSearchParam);
    }

    /**
     * 精确查询单个课程信息
     * @param id
     * @return
     */
    @GetMapping("/getall/{id}")
    public Map<String, CoursePub> getall(@PathVariable("id") String id) {
        return esCourseService.getall(id);
    }

    /**
     * 根据课程计划id查询媒体资源信息
     * @param teachplanId
     * @return
     */
    @GetMapping(value="/getmedia/{teachplanId}")
    public TeachplanMediaPub getmedia(@PathVariable("teachplanId") String teachplanId) {
        //将课程计划id放在数组中，为调用service作准备
        String[] teachplanIds = new String[]{teachplanId};
        //通过service查询ES获取课程媒资信息
        QueryResponseResult<TeachplanMediaPub> mediaPubQueryResponseResult =
                esCourseService.getmedia(teachplanIds);
        QueryResult<TeachplanMediaPub> queryResult = mediaPubQueryResponseResult.getQueryResult();
        if(queryResult!=null&& queryResult.getList()!=null
                && queryResult.getList().size()>0){
            //返回课程计划对应课程媒资
            return queryResult.getList().get(0);
        }
        return new TeachplanMediaPub();
    }
}
