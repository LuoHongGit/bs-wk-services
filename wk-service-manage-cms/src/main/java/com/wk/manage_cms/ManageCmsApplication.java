package com.wk.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName cms微服务主启动类
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/11/27 16:34
 * @Version 1.0
 **/
@SpringBootApplication
@EntityScan("com.wk.framework.domain.cms")
@ComponentScan(basePackages = "com.wk.api")
@ComponentScan(basePackages = "com.wk.framework")
@ComponentScan(basePackages = "com.wk.manage_cms")
public class ManageCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class, args);
    }

}
