package com.wk.manage_cms.controller;

import com.wk.api.cms.CmsPageControllerApi;
import com.wk.framework.domain.cms.CmsPage;
import com.wk.framework.domain.cms.request.QueryPageRequest;
import com.wk.framework.domain.cms.response.CmsPageResult;
import com.wk.framework.domain.cms.response.CmsPostPageResult;
import com.wk.framework.domain.course.CourseBase;
import com.wk.framework.model.response.CommonCode;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.framework.model.response.QueryResult;
import com.wk.framework.model.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @ClassName CmsPageController
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/11/27 16:36
 * @Version 1.0
 **/
@RestController
public class CmsPageController implements CmsPageControllerApi {

    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult list(@PathVariable("page") int page,@PathVariable("size") int size, QueryPageRequest request){

        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        ArrayList<CmsPage> list = new ArrayList<>();
        list.add(cmsPage);
        QueryResult<CmsPage> queryResult = new QueryResult<>();

        queryResult.setList(list);
        queryResult.setTotal(1);

        QueryResponseResult<Object> result = new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);

        return result;
    }


    @Override
    public QueryResponseResult<CourseBase> findList(int page, int size, QueryPageRequest request) {
        return null;
    }

    @Override
    public CmsPageResult addPage(CmsPage cmsPage) {
        return null;
    }

    @Override
    public CmsPage findById(String id) {
        return null;
    }

    @Override
    public CmsPageResult editPage(String id, CmsPage cmsPage) {
        return null;
    }

    @Override
    public ResponseResult delPage(String id) {
        return null;
    }

    @Override
    public ResponseResult postPage(String id) {
        return null;
    }

    @Override
    public CmsPageResult save(CmsPage cmsPage) {
        return null;
    }

    @Override
    public CmsPostPageResult postPageQuick(CmsPage cmsPage) {
        return null;
    }
}
