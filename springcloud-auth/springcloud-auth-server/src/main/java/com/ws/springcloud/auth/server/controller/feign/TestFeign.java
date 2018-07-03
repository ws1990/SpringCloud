package com.ws.springcloud.auth.server.controller.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/6/28 16:01
 * @version 1.0
 */
@RestController
@RequestMapping("/feign/test")
public class TestFeign {

    @GetMapping("/index")
    public String index() {
        return "Hello World! And this is auth.";
    }

}
