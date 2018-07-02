package com.ws.springcloud.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description
 *
 * @author 王松
 * @date 2018/6/28 15:24
 * @version 1.0
 */
@Configuration
public class SecurityConfig {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("jwtSigningKey");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore jwtTokenStore(JwtAccessTokenConverter converter) {
        return new JwtTokenStore(converter);
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
        @Autowired
        private TokenStore tokenStore;
        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources
                    .tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            System.out.println(SecurityConfig.class.getName() + ".configure(HttpSecurity http)");
            http.authorizeRequests().anyRequest().authenticated();
        }

    }

}
