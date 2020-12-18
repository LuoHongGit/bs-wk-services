package com.wk.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName LearningApplication
 * @Description 学习中心微服务启动类
 * @Author LuoHong
 * @Date 2020/12/18 20:31
 * @Version 1.0
 **/

@EnableFeignClients
@EnableDiscoveryClient//从Eureka Server获取服务
@SpringBootApplication
@EntityScan("com.wk.framework.domain")
@ComponentScan("com.wk.framework")
@ComponentScan("com.wk.api")
@ComponentScan("com.wk.learning")
public class LearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }

}
