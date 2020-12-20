package com.wk.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName UcenterApplication
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/12/19 22:55
 * @Version 1.0
 **/
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient//从Eureka Server获取服务
@EntityScan("com.wk.framework.domain")
@ComponentScan("com.wk.framework")
@ComponentScan("com.wk.api")
@ComponentScan("com.wk.ucenter")
@MapperScan("com.wk.ucenter.dao")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
