package com.wk.api.cms;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value="cms预览接口接口")
public interface CmsPagePreviewControllerApi {

    @ApiOperation("页面预览")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageId",value = "cms页面id",required=true,paramType="path",dataType="string"),
    })
    public void preview(String pageId);

}
