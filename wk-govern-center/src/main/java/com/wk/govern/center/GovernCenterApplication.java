package com.wk.govern.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName GovernCenterApplication
 * @Description 注册中心启动类
 * @Author LuoHong
 * @Date 2020/12/9 17:10
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class GovernCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GovernCenterApplication.class,args);
    }
}
