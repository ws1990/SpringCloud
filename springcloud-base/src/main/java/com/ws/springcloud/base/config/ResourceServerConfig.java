package com.ws.springcloud.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @description
 *
 * @author 王松
 * @date 2018/6/28 15:24
 * @version 1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        System.out.println(ResourceServerConfig.class.getName() + ".configure(HttpSecurity http)");
        http.authorizeRequests().anyRequest().authenticated();
    }

}
