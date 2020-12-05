package com.wk.manage_cms.service;

import com.wk.framework.domain.cms.CmsConfig;
import com.wk.framework.domain.cms.CmsTemplate;
import com.wk.manage_cms.dao.CmsConfigRepository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName CmsConfigService
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/12/4 20:29
 * @Version 1.0
 **/
@Service
public class CmsConfigService {
    @Resource
    private CmsConfigRepository cmsConfigRepository;

    /**
     * 查询全部
     * @return
     */
    public List<CmsConfig> findAll(){
        return cmsConfigRepository.findAll();
    }

    /**
     * 通过id查询cmsconfig
     * @param id
     * @return
     */
    public CmsConfig findById(String id){
        Optional<CmsConfig> opt = cmsConfigRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

        return null;
    }
}
