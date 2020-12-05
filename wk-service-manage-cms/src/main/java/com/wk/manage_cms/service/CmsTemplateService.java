package com.wk.manage_cms.service;

import com.wk.framework.domain.cms.CmsConfig;
import com.wk.framework.domain.cms.CmsTemplate;
import com.wk.manage_cms.dao.CmsConfigRepository;
import com.wk.manage_cms.dao.CmsTemplateRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CmsConfigService
 * @Description CmsTemplate业务层
 * @Author LuoHong
 * @Date 2020/12/4 20:29
 * @Version 1.0
 **/
@Service
public class CmsTemplateService {
    @Resource
    private CmsTemplateRepository cmsTemplateRepository;

    public List<CmsTemplate> findAll(){
        return cmsTemplateRepository.findAll();
    }
}
