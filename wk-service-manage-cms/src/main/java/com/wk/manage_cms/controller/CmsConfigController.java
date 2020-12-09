package com.wk.manage_cms.controller;

import com.wk.api.cms.CmsConfigControllerApi;
import com.wk.framework.domain.cms.CmsConfig;
import com.wk.manage_cms.service.CmsConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CmsConfigController
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/12/4 20:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi {

    @Resource
    private CmsConfigService cmsConfigService;

    @GetMapping("/get/{id}")
    public CmsConfig findById(@PathVariable("id") String id){
        return cmsConfigService.findById(id);
    }

}
