package com.wk.api.mdia;

import com.wk.framework.domain.media.request.QueryMediaFileRequest;
import com.wk.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "媒体文件管理",description = "媒体文件管理接口")
public interface MediaFileControllerApi {

    @ApiOperation("分页查询文件列表")
    QueryResponseResult findList(int page, int size, QueryMediaFileRequest queryMediaFileRequest);

}
