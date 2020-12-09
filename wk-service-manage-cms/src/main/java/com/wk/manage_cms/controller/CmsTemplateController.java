package com.wk.manage_cms.controller;

import com.wk.api.cms.CmsTemplateControllerApi;
import com.wk.framework.domain.cms.CmsConfig;
import com.wk.framework.domain.cms.CmsTemplate;
import com.wk.manage_cms.dao.CmsTemplateRepository;
import com.wk.manage_cms.service.CmsConfigService;
import com.wk.manage_cms.service.CmsTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CmsTemplateController
 * @Description CmsTemplate控制层
 * @Author LuoHong
 * @Date 2020/12/4 20:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi {

    @Resource
    private CmsTemplateService cmsTemplateService;

    @GetMapping("/findAll")
    public List<CmsTemplate> findAll(){
        return cmsTemplateService.findAll();
    }

}
