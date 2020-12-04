package com.wk.manage_cms.dao;

import com.wk.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName CmsPageRepository
 * @Description cms持久层接口
 * @Author LuoHong
 * @Date 2020/11/27 23:22
 * @Version 1.0
 **/
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
    CmsPage findByPageNameAndPageWebPathAndSiteId(String pageName,String pageWebPath, String siteId);
}
