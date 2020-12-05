package com.wk.manage_cms.service;

import com.wk.framework.domain.cms.CmsSite;
import com.wk.manage_cms.dao.CmsSiteRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CmsSiteService
 * @Description CmsSite业务层
 * @Author LuoHong
 * @Date 2020/12/4 20:40
 * @Version 1.0
 **/
@Service
public class CmsSiteService {
    @Resource
    private CmsSiteRepository cmsSiteRepository;

    public List<CmsSite> findAll(){
        return cmsSiteRepository.findAll();
    }
}
