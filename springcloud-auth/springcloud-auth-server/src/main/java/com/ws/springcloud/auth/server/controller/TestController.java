package com.ws.springcloud.auth.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/6/29 15:47
 * @version 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/")
    public String index() {
        return "AuthTestController.index";
    }

}
