package com.wk.api.cms;

import com.wk.framework.domain.cms.CmsSite;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api("站点管理接口")
public interface CmsSiteControllerApi {

    @ApiOperation("查询所有站点")
    public List<CmsSite> findAll();

}
