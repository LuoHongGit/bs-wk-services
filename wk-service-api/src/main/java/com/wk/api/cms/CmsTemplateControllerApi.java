package com.wk.api.cms;

import com.wk.framework.domain.cms.CmsTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api("cms模板接口")
public interface CmsTemplateControllerApi {

    @ApiOperation("查询所有模板信息")
    public List<CmsTemplate> findAll();

}
