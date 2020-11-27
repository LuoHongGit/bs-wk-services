package com.wk.framework.domain.cms.request;

import lombok.Data;

/**
 * CMS页面查询参数封装类
 */
@Data
public class QueryPageRequest {
    //站点id
    private String siteId;
    //页面ID
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模版id
    private String templateId;
    //类型（静态/动态）
    private String pageType;
}
