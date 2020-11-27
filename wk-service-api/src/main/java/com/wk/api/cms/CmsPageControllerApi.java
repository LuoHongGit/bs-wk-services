package com.wk.api.cms;

import com.wk.framework.domain.cms.CmsPage;
import com.wk.framework.domain.cms.request.QueryPageRequest;
import com.wk.framework.domain.cms.response.CmsPageResult;
import com.wk.framework.domain.cms.response.CmsPostPageResult;
import com.wk.framework.domain.course.CourseBase;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * cms的控制层API接口类
 */
@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    //分页条件查询页面
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    QueryResponseResult<CourseBase> findList(int page, int size, QueryPageRequest request);

    //新增页面
    @ApiOperation("新增页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="cmsPage",value = "页面数据对象",required=true),
    })
    CmsPageResult addPage(CmsPage cmsPage);

    //根据id查询页面数据
    @ApiOperation("根据id查询页面数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "页面id",paramType="path",required=true),
    })
    CmsPage findById(String id);

    //修改页面
    @ApiOperation("修改页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "页面id",required=true,paramType="path"),
            @ApiImplicitParam(name="cmsPage",value = "页面实体对象",required=true,paramType="body"),
    })
    CmsPageResult editPage(String id, CmsPage cmsPage);

    @ApiOperation("删除页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "页面id",required=true,paramType="path"),
    })
    ResponseResult delPage(String id);

    @ApiOperation("发布页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "页面id",required=true,paramType="path"),
    })
    ResponseResult postPage(String id);

    @ApiOperation("保存页面")
    CmsPageResult save(CmsPage cmsPage);

    @ApiOperation("一键发布页面")
    public CmsPostPageResult postPageQuick(CmsPage cmsPage);
}
