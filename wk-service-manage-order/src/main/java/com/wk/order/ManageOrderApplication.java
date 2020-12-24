package com.wk.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * 订单微服务主启动类
 */
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan(value={"com.wk.framework.domain.order","com.wk.framework.domain.task"})//扫描实体类
@ComponentScan(basePackages={"com.wk.api"})//扫描接口
@ComponentScan(basePackages={"com.wk.framework"})//扫描framework中通用类
@ComponentScan(basePackages={"com.wk.order"})//扫描本项目下的所有类
@SpringBootApplication
public class ManageOrderApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ManageOrderApplication.class, args);
    }
}