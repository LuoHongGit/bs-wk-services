package com.wk.manage_cms.dao;

import com.wk.framework.domain.cms.CmsConfig;
import com.wk.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CmsConfig持久层接口
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {
}
