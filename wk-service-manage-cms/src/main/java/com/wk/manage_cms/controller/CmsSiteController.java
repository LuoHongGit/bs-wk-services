package com.wk.manage_cms.controller;

import com.wk.api.cms.CmsSiteControllerApi;
import com.wk.framework.domain.cms.CmsConfig;
import com.wk.framework.domain.cms.CmsSite;
import com.wk.manage_cms.service.CmsConfigService;
import com.wk.manage_cms.service.CmsSiteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CmsSiteController
 * @Description CmsSite控制层
 * @Author LuoHong
 * @Date 2020/12/4 20:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cms/site")
public class CmsSiteController implements CmsSiteControllerApi {

    @Resource
    private CmsSiteService cmsSiteService;

    @GetMapping("/findAll")
    public List<CmsSite> findAll(){
        return cmsSiteService.findAll();
    }

}
