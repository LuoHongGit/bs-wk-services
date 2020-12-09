package com.wk.manage_course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * @version 1.0
 **/

//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.wk.framework.domain.course")//扫描实体类
@ComponentScan(basePackages={"com.wk.api"})//扫描接口
@ComponentScan(basePackages={"com.wk.manage_course"})
@ComponentScan(basePackages={"com.wk.framework"})//扫描common下的所有类
@MapperScan(basePackages = {"com.wk.manage_course.dao"})
public class ManageCourseApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ManageCourseApplication.class, args);
    }
}
