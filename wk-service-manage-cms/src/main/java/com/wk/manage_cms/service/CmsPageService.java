package com.wk.manage_cms.service;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.wk.framework.domain.cms.CmsConfig;
import com.wk.framework.domain.cms.CmsPage;
import com.wk.framework.domain.cms.CmsTemplate;
import com.wk.framework.domain.cms.request.QueryPageRequest;
import com.wk.framework.domain.cms.response.CmsCode;
import com.wk.framework.domain.cms.response.CmsPageResult;
import com.wk.framework.domain.course.CourseBase;
import com.wk.framework.exception.ExceptionCast;
import com.wk.framework.model.response.CommonCode;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.framework.model.response.QueryResult;
import com.wk.framework.model.response.ResponseResult;
import com.wk.manage_cms.config.RabbitmqConfig;
import com.wk.manage_cms.dao.CmsConfigRepository;
import com.wk.manage_cms.dao.CmsPageRepository;
import com.wk.manage_cms.dao.CmsTemplateRepository;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName CmsPageService
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/11/28 13:37
 * @Version 1.0
 **/
@Service
public class CmsPageService {

    @Resource
    private CmsPageRepository cmsPageRepository;

    @Resource
    private CmsConfigRepository cmsConfigRepository;

    @Resource
    private CmsTemplateRepository cmsTemplateRepository;

    @Resource
    private CmsConfigService cmsConfigService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private GridFSBucket gridFSBucket;

    @Resource
    private GridFsTemplate gridFsTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 通过pageid生成预览页面
     * @param pageId
     * @return
     */
    public String getHtmlPageById(String pageId) {
        //获取模型数据
        Map model = this.getModelById(pageId);

        //判空
        if (model == null) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }

        //获取模板内容
        String templateStr = this.getTemplateStrByPageId(pageId);

        //执行静态化
        String content = this.generateHtml(templateStr, model);

        if (StringUtils.isEmpty(content)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }

        return content;
    }

    /**
     * 使用模板和数据模型生成静态页面字符串
     * @param templateStr
     * @param model
     * @return
     */
    private String generateHtml(String templateStr, Map model) {
        try {
            //创建配置对象
            Configuration cfg = new Configuration(Configuration.getVersion());

            //创建模板加载器
            StringTemplateLoader templateLoader = new StringTemplateLoader();

            //设置模板
            templateLoader.putTemplate("template", templateStr);

            //加载器添加到配置中
            cfg.setTemplateLoader(templateLoader);

            //获取模板
            Template template = cfg.getTemplate("template", "utf-8");

            //生成静态内容
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 通过pageid获取模型内容
     * @param pageId
     * @return
     */
    private Map getModelById(String pageId) {
        //通过id获取cmspage对象
        CmsPage cmsPage = this.findById(pageId);

        //判空
        if (cmsPage == null) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }

        //获取dataUrl
        String dataUrl = cmsPage.getDataUrl();

        //判空
        if (StringUtils.isEmpty(dataUrl)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }

        //请求数据url获取结果
        ResponseEntity<Map> entity = restTemplate.getForEntity(dataUrl, Map.class);

        return entity.getBody();
    }

    /**
     * 通过pageId获取模板字符串
     * @return
     */
    private String getTemplateStrByPageId(String pageId) {
        //通过id获取cmspage对象
        CmsPage cmsPage = this.findById(pageId);

        //判空
        if (cmsPage == null) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }

        //获取templateId
        String templateId = cmsPage.getTemplateId();

        //判空
        if (StringUtils.isEmpty(templateId)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        //通过模板id获取模板
        Optional<CmsTemplate> opt = cmsTemplateRepository.findById(templateId);

        //判空
        if (opt.isPresent()) {
            CmsTemplate cmsTemplate = opt.get();
            String templateFileId = cmsTemplate.getTemplateFileId();

            //取出模板文件内容
            GridFSFile gridFSFile =
                    gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));
            //打开下载流对象
            GridFSDownloadStream gridFSDownloadStream =
                    gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            //创建GridFsResource
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            try {
                String content = IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 按条件分页查询页面
     * @param page
     * @param size
     * @param request
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest request) {
        //参数判断
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 20;
        }

        //page从0开始
        --page;
        Pageable pageable = new PageRequest(page, size);

        //创建匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());

        //创建模型对象并赋值
        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNoneEmpty(request.getSiteId())) {
            cmsPage.setSiteId(request.getSiteId());
        }
        if (StringUtils.isNoneEmpty(request.getPageAliase())) {
            cmsPage.setPageAliase(request.getPageAliase());
        }

        //创建查询条件对象
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        //分页查询
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);

        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(all.getContent());

        QueryResponseResult<CmsPage> result = new QueryResponseResult<CmsPage>(CommonCode.SUCCESS, queryResult);

        return result;
    }

    /**
     * 添加页面
     * @param cmsPage
     * @return
     */
    public CmsPageResult addPage(@RequestBody CmsPage cmsPage) {
        if (cmsPage == null) {
            return  new CmsPageResult(CommonCode.FAIL,null);
        }

        //根据pageName，pageWebPath，siteId查询页面
        CmsPage res = cmsPageRepository.findByPageNameAndPageWebPathAndSiteId(cmsPage.getPageName(), cmsPage.getPageWebPath(), cmsPage.getSiteId());

        if(res !=null){
            //校验页面是否存在，已存在则抛出异常
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTS);
        }

        //保存页面
        cmsPage.setPageId(null);
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
    }

    /**
     * 通过id查询页面
     * @param id
     * @return
     */
    public CmsPage findById(String id) {
        Optional<CmsPage> opt = cmsPageRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    /**
     * 编辑页面
     * @param id
     * @param cmsPage
     * @return
     */
    public CmsPageResult editPage(String id, CmsPage cmsPage) {
        //根据id查询页面信息
        CmsPage one = this.findById(id);
        if (one != null) {
            one.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            one.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            one.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            one.setPageName(cmsPage.getPageName());
            //更新访问路径
            one.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //更新页面类型
            one.setPageType(cmsPage.getPageType());
            //更新dataurl
            one.setDataUrl(cmsPage.getDataUrl());
            //执行更新
            CmsPage save = cmsPageRepository.save(one);
            if (save != null) {
                //返回成功
                CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS, save);
                return cmsPageResult;
            }
        }
        //返回失败
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    /**
     * 删除页面
     * @param id
     * @return
     */
    public ResponseResult delPage(String id) {
        //根据id查询页面
        Optional<CmsPage> opt = cmsPageRepository.findById(id);

        //结果不为空
        if (opt.isPresent()) {
            //删除页面
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }

        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 页面发布
     * @param pageId
     * @return
     */
    public ResponseResult postPage(String pageId) {
        //获取静态化页面
        String pageStr = this.getHtmlPageById(pageId);

        //将页面存入gridfs
        this.savePage(pageId, pageStr);

        //发送消息
        this.sendPostPage(pageId);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 保存页面
     * @param pageId
     * @param pageStr
     * @return
     */
    public CmsPage savePage(String pageId, String pageStr) {
        //通过id查询页面
        Optional<CmsPage> opt = cmsPageRepository.findById(pageId);

        //判空
        if (!opt.isPresent()) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }

        //获取cmspage
        CmsPage cmsPage = opt.get();

        //判断是否存在html文件
        String htmlFileId = cmsPage.getHtmlFileId();
        if (!StringUtils.isEmpty(htmlFileId)) {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(htmlFileId)));
        }

        //创建输入流
        InputStream inputStream = IOUtils.toInputStream(pageStr);

        //保存
        ObjectId objectId = gridFsTemplate.store(inputStream, cmsPage.getPageName());

        //更新cmspage数据并保存
        cmsPage.setHtmlFileId(objectId.toString());
        cmsPageRepository.save(cmsPage);

        return cmsPage;
    }

    /**
     * 发送消息到mq
     * @param pageId
     */
    public void sendPostPage(String pageId) {
        //根据id查询页面
        Optional<CmsPage> opt = cmsPageRepository.findById(pageId);

        //判空
        if (!opt.isPresent()) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }

        //获取cmsPage
        CmsPage cmsPage = opt.get();

        //创建Map
        Map<String, String> map = new HashMap<>();

        //向Map中添加属性
        map.put("pageId", pageId);

        //将map转换为json
        String msg = JSON.toJSONString(map);

        //发送
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.EX_ROUTING_CMS_POSTPAGE, cmsPage.getSiteId(), msg);
    }
}
