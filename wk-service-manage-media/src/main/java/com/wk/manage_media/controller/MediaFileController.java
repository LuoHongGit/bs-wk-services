package com.wk.manage_media.controller;

import com.wk.framework.domain.media.MediaFile;
import com.wk.framework.domain.media.request.QueryMediaFileRequest;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.manage_media.service.MediaFileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName MediaFileController
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/12/16 16:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/media/file")
public class MediaFileController {

    @Resource
    private MediaFileService mediaFileService;

    /**
     * 分页查询媒体文件
     * @param page
     * @param size
     * @param queryMediaFileRequest
     * @return
     */
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, QueryMediaFileRequest queryMediaFileRequest){
        return mediaFileService.findList(page, size, queryMediaFileRequest);
    }
}
