package com.wk.api.cms;

import com.wk.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="cms配置管理接口")
public interface CmsConfigControllerApi {
    /**
     * 通过id查询Cms配置
     * @param id
     * @return
     */
    @ApiOperation("通过id查询cms配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "cms配置id",required=true,paramType="path",dataType="string"),
    })
    CmsConfig findById(String id);
}
