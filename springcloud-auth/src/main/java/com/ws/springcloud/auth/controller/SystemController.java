package com.ws.springcloud.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/6/28 16:00
 * @version 1.0
 */
@RestController
@RequestMapping("system")
public class SystemController {

    @GetMapping("/user/current")
    public Principal current(Principal principal) {
        System.out.println("SystemController.current：" + principal);
        return principal;
    }

}
