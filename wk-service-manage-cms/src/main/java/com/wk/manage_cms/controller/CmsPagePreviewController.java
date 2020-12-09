package com.wk.manage_cms.controller;

import com.wk.api.cms.CmsPagePreviewControllerApi;
import com.wk.framework.web.BaseController;
import com.wk.manage_cms.service.CmsPageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * 页面预览控制器
 */
@Controller
public class CmsPagePreviewController extends BaseController implements CmsPagePreviewControllerApi {
    @Autowired
    private CmsPageService cmsPageService;

    /**
     * 页面预览功能
     * @param pageId
     */
    @GetMapping("/cms/preview/{pageId}")
    public void preview(@PathVariable("pageId") String pageId){
        String content = cmsPageService.getHtmlPageById(pageId);

        if (!StringUtils.isEmpty(content)) {
            try {
                this.response.setContentType("text/html;charset=utf-8");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(content.getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
