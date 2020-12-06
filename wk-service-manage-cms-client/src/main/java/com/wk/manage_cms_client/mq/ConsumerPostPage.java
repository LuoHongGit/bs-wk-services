package com.wk.manage_cms_client.mq;

import com.alibaba.fastjson.JSON;
import com.wk.framework.domain.cms.CmsPage;
import com.wk.manage_cms_client.dao.CmsPageRepository;
import com.wk.manage_cms_client.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

@Component
public class ConsumerPostPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerPostPage.class);
    @Resource
    CmsPageRepository cmsPageRepository;
    @Resource
    PageService pageService;

    @RabbitListener(queues = {"${wk.mq.queue}"})
    public void postPage(String msg) {
        //解析消息
        Map map = JSON.parseObject(msg, Map.class);
        LOGGER.info("receive cms post page:{}", msg.toString());
        //取出页面id
        String pageId = (String) map.get("pageId");
        //查询页面信息
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent()) {
            LOGGER.error("receive cms post page,cmsPage is null:{}", msg.toString());
            return;
        } //将页面保存到服务器物理路径
        pageService.savePageToServerPath(pageId);
    }
}