package com.wk.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName 认证中心启动类
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/12/19 12:45
 * @Version 1.0
 **/

@EnableFeignClients
@EnableDiscoveryClient//从Eureka Server获取服务
@SpringBootApplication
@EntityScan("com.wk.framework.domain")
@ComponentScan("com.wk.framework")
@ComponentScan("com.wk.api")
@ComponentScan("com.wk.auth")
public class UcenterAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterAuthApplication.class,args);
    }
}
