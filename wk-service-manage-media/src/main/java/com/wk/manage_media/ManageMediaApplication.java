package com.wk.manage_media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ManageMediaApplication
 * @Description 媒体资源管理启动类
 * @Author LuoHong
 * @Date 2020/12/15 14:28
 * @Version 1.0
 **/

@SpringBootApplication
@EntityScan("com.wk.framework.domain")
@ComponentScan("com.wk.framework")
@ComponentScan("com.wk.api")
@ComponentScan("com.wk.manage_media")
@EnableDiscoveryClient//从Eureka Server获取服务
public class ManageMediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageMediaApplication.class, args);
    }

}
