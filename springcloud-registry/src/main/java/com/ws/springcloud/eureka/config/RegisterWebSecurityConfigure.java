package com.ws.springcloud.eureka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Description：
 *
 * @author wangsong
 * @version 1.0
 * @since 11/26/2020 11:21 AM
 */
@EnableWebSecurity
public class RegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 允许其他service注册时跨域访问
        http.csrf().ignoringAntMatchers("/eureka/**");

        // local环境下，不需要登录验证
        // 其他环境下，必须验证
        if (isLocalEnv()) {
            http.authorizeRequests()
                    .anyRequest().permitAll().and().logout().permitAll();
        } else {
            super.configure(http);
        }
    }

    private boolean isLocalEnv() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length == 0) {
            return false;
        }

        for (String profile : activeProfiles) {
            if ("local".equalsIgnoreCase(profile)) {
                return true;
            }
        }

        return false;
    }
}
