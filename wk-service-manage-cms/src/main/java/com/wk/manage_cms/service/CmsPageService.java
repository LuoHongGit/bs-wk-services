package com.wk.manage_cms.service;

import com.wk.framework.domain.cms.CmsPage;
import com.wk.framework.domain.cms.request.QueryPageRequest;
import com.wk.framework.domain.cms.response.CmsCode;
import com.wk.framework.domain.cms.response.CmsPageResult;
import com.wk.framework.domain.course.CourseBase;
import com.wk.framework.exception.ExceptionCast;
import com.wk.framework.model.response.CommonCode;
import com.wk.framework.model.response.QueryResponseResult;
import com.wk.framework.model.response.QueryResult;
import com.wk.framework.model.response.ResponseResult;
import com.wk.manage_cms.dao.CmsPageRepository;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
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

}
