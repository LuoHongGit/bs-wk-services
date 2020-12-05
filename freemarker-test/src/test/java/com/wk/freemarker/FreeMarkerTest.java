package com.wk.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FreeMarkerTest
 * @Description FreeMarker测试类
 * @Author LuoHong
 * @Date 2020/12/3 22:39
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class FreeMarkerTest {

    /**
     * 文件静态化测试
     * @throws Exception
     */
    @Test
    public void staticTest1() throws Exception{
        //创建配置类
        Configuration config = new Configuration(Configuration.getVersion());

        //获取类路径
        String path = this.getClass().getResource("/").getPath();

        //设置模板路径和编码格式
        config.setDirectoryForTemplateLoading(new File(path + "templates/"));

        //获取模板
        Template template = config.getTemplate("test2.ftl", "UTF-8");

        //创建数据模型
        Map<String,Object> map = new HashMap<>();
        map.put("name","黑马程序员");

        //执行静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

        System.out.println(content);
    }

    /**
     * 字符串静态化测试
     * @throws Exception
     */
    @Test
    public void staticTest2() throws Exception{

        //创建配置对象
        Configuration config = new Configuration(Configuration.getVersion());

        //创建模板字符串
        String templateString="" +
                "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                " 名称：${name}\n" +
                " </body>\n" +
                "</html>";

        //创建字符串模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",templateString);
        config.setTemplateLoader(stringTemplateLoader);

        //得到模板
        Template template = config.getTemplate("template","utf‐8");

        //创建数据模型
        Map<String,Object> map = new HashMap<>();
        map.put("name","黑马程序员");

        //执行静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

        System.out.println(content);

    }
}
