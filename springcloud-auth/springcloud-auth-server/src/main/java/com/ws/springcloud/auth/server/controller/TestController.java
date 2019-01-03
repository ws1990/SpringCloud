package com.ws.springcloud.auth.server.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王松
 * @version 1.0
 * @description
 * @date 2018/6/29 15:47
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private ConfigTest configTest;

    @GetMapping("/")
    public String index() {
        return "AuthTestController.index";
    }

    @GetMapping("config")
    public Object getConfig() {
        return configTest;
    }


    @Data
    @Component
    @ConfigurationProperties(prefix = "config.test")
    public static class ConfigTest {
        private String key;
        private String commonKey;
    }

}
