package com.wk.govern.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName GatewayApplication
 * @Description 网关启动类
 * @Author LuoHong
 * @Date 2020/12/20 16:44
 * @Version 1.0
 **/
@SpringBootApplication
@EnableZuulProxy
@ComponentScan("com.wk.framework")
@ComponentScan("com.wk.api")
@ComponentScan("com.wk.govern.gateway")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
