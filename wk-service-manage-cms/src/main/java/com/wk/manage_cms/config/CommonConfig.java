package com.wk.manage_cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName CommonConfig
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/12/4 21:18
 * @Version 1.0
 **/
@Configuration
public class CommonConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
