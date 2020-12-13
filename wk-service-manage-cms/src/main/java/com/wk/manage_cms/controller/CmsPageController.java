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
import com.wk.manage_cms.service.CmsPageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @ClassName CmsPageController
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/11/27 16:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Resource
    private CmsPageService cmsPageService;

    /**
     * 页面发布
     * @param id
     * @return
     */
    @PostMapping("/postPage/{id}")
    public ResponseResult postPage(@PathVariable("id") String id) {
        return cmsPageService.postPage(id);
    }

    /**
     * 按条件分页查询页面
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult<CmsPage> findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest request) {
        return cmsPageService.findList(page,size,request);
    }

    /**
     * 添加页面
     * @param cmsPage
     * @return
     */
    @PostMapping("/add")
    public CmsPageResult addPage(@RequestBody CmsPage cmsPage) {
        return cmsPageService.addPage(cmsPage);
    }

    /**
     * 通过id查询页面
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        return cmsPageService.findById(id);
    }

    /**
     * 编辑页面
     * @param id
     * @param cmsPage
     * @return
     */
    @PutMapping("/edit/{id}")
    public CmsPageResult editPage(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return cmsPageService.editPage(id,cmsPage);
    }

    /**
     * 删除页面
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public ResponseResult delPage(@PathVariable("id") String id) {
        return cmsPageService.delPage(id);
    }

    /**
     * 保存或者更新页面
     * @param cmsPage
     * @return
     */
    @PostMapping("/save")
    public CmsPageResult save(@RequestBody CmsPage cmsPage) {
        return cmsPageService.saveOrUpdate(cmsPage);
    }

    @PostMapping("/postPageQuick")
    public CmsPostPageResult postPageQuick(@RequestBody CmsPage cmsPage) {
        return cmsPageService.postPageQuick(cmsPage);
    }
}
