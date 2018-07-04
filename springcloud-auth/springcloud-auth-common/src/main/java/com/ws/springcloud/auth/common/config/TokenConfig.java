package com.ws.springcloud.auth.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/7/4 10:04
 * @version 1.0
 */
@Configuration
public class TokenConfig {

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

}
