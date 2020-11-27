package com.wk.api.filesystem;

import com.wk.framework.domain.filesystem.response.UploadFileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "文件系统管理接口", description = "提供文件的增删改查操作")
public interface FileSystemControllerApi {
    @ApiOperation("上传文件")
    UploadFileResult upload(MultipartFile file,
                            String filetag,
                            String businesskey,
                            String metadata
                            );

}
