package com.wk.manage_media_process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ManageMediaProcessorApplication
 * @Description 媒体资源处理客户端
 * @Author LuoHong
 * @Date 2020/12/15 22:12
 * @Version 1.0
 **/
@SpringBootApplication
@EntityScan("com.wk.framework.domain")
@ComponentScan("com.wk.framework")
@ComponentScan("com.wk.api")
@ComponentScan("com.wk.manage_media_process")
public class ManageMediaProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageMediaProcessorApplication.class, args);
    }
}
