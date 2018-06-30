package com.ws.springcloud.base.controller;

import com.ws.springcloud.api.feign.AuthFeignTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/6/28 16:14
 * @version 1.0
 */
@RequestMapping("test")
@RestController
public class BaseTestController {

    @Autowired
    private AuthFeignTestService authFeignTestService;

    @GetMapping("auth/feign")
    public String authFeign() {
        return authFeignTestService.index();
    }

}
