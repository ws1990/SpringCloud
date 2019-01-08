package com.ws.springcloud.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Description:
 *
 * @author 王松
 * @version 1.0
 * @date 19-1-8 上午11:16
 */
@SpringBootApplication
@MapperScan("com.ws.springcloud.auth.server.dao.mapper")
@ComponentScan(
        basePackages = {"com.ws.springcloud"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {AuthServerApplication.class})
        })
public class AuthServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerTestApplication.class, args);
    }

}
