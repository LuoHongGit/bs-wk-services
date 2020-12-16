package com.wk.manage_media.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName CommonCOnfig
 * @Description 通用配置类
 * @Author LuoHong
 * @Date 2020/12/15 14:31
 * @Version 1.0
 **/
@Configuration
public class CommonConfig {

    /**
     * rest客户端
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }


}
