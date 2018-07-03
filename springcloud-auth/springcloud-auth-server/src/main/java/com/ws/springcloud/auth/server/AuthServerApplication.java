package com.ws.springcloud.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/7/3 10:04
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ws.springcloud")
@MapperScan("com.ws.springcloud.auth.server.dao.mapper")
@ComponentScan(basePackages = "com.ws.springcloud")
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}
