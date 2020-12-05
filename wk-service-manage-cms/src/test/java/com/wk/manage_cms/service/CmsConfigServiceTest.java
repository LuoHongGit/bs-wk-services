package com.wk.manage_cms.service;

import com.wk.framework.domain.cms.CmsConfig;
import com.wk.manage_cms.dao.CmsConfigRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CmsServiceTest
 * @Description CmsService测试类
 * @Author LuoHong
 * @Date 2020/12/4 20:11
 * @Version 1.0
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsConfigServiceTest {

    @Resource
    private CmsConfigService configService;

    @Test
    public void findAllTest(){
        List<CmsConfig> all = configService.findAll();

        System.out.println(all);
    }
}
