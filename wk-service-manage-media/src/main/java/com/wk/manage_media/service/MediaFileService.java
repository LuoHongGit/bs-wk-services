package com.wk.manage_media.service;

import com.wk.framework.domain.media.MediaFile;
import com.wk.framework.domain.media.request.QueryMediaFileRequest;
import com.wk.framework.model.response.CommonCode;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.framework.model.response.QueryResult;
import com.wk.manage_media.dao.MediaFileRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MediaFileService
 * @Description 媒体资源业务层
 * @Author LuoHong
 * @Date 2020/12/16 16:18
 * @Version 1.0
 **/
@Service
public class MediaFileService {

    @Resource
    private MediaFileRepository mediaFileRepository;

    public QueryResponseResult findList(int page, int size, QueryMediaFileRequest queryMediaFileRequest){
        //参数合法校验
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }

        --page;

        //设置标签和原始名称模糊匹配
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("tag", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("fileOriginalName", ExampleMatcher.GenericPropertyMatchers.contains());

        //创建实体对象
        MediaFile mediaFile = new MediaFile();

        //属性判断
        if (!StringUtils.isBlank(queryMediaFileRequest.getTag())) {
            mediaFile.setTag(queryMediaFileRequest.getTag());
        }
        if (!StringUtils.isBlank(queryMediaFileRequest.getFileOriginalName())) {
            mediaFile.setFileOriginalName(queryMediaFileRequest.getFileOriginalName());
        }
        if (!StringUtils.isBlank(queryMediaFileRequest.getProcessStatus())) {
            mediaFile.setProcessStatus(queryMediaFileRequest.getProcessStatus());
        }

        //创建Example对象
        Example<MediaFile> example = Example.of(mediaFile, exampleMatcher);

        //设置分页参数
        Pageable pageable = new PageRequest(page,size);

        //查询
        Page<MediaFile> res = mediaFileRepository.findAll(example, pageable);

        //获取总条数
        long totalElements = res.getTotalElements();

        //获取内容
        List<MediaFile> content = res.getContent();

        //创建响应结果对象返回
        QueryResult<MediaFile> queryResult = new QueryResult<>();
        queryResult.setTotal(totalElements);
        queryResult.setList(content);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

}
