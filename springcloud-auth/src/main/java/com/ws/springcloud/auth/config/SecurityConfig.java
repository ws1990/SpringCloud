package com.ws.springcloud.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
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
    public TokenStore jwtTokenStore(JwtAccessTokenConverter converter) {
        return new JwtTokenStore(converter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("jwtSigningKey");
        return jwtAccessTokenConverter;
    }

    @Configuration
    protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        /**
         * 必须配置，否则oauth2的password模式将会报错
         * @return
         * @throws Exception
         */
        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
        @Autowired
        private AuthenticationManager auth;
        @Autowired
        private TokenStore tokenStore;
        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        /**
         * 安全访问配置
         * @param security
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            System.out.println("【AuthorizationServerConfig.configure(AuthorizationServerSecurityConfigurer security)】");
            security
                    //允许表单认证，如果不配置，/oauth/token接口将一直抛出Access is denied异常
                    .allowFormAuthenticationForClients()
                    //设置密码加密方式，必须配置
                    .passwordEncoder(new BCryptPasswordEncoder());
        }

        /**
         * 客户端授权模式配置
         * @param clients
         * @throws Exception
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            System.out.println("【AuthorizationServerConfig.configure(ClientDetailsServiceConfigurer clients)】");
            clients.inMemory()
                    //app通过password验证
                    .withClient("app")
                    .authorizedGrantTypes("password", "refresh_token")
                    .accessTokenValiditySeconds(3600)
                    .refreshTokenValiditySeconds(15 * 24 * 3600)
                    //加密方式同SecurityConfig配置的一致
                    .secret(new BCryptPasswordEncoder().encode("app"))

                    //web通过password验证
                    .and()
                    .withClient("web")
                    .authorizedGrantTypes("password", "refresh_token")
                    .accessTokenValiditySeconds(3600)
                    //加密方式同SecurityConfig配置的一致
                    .secret(new BCryptPasswordEncoder().encode("web"))

                    //内部服务通过client_credentials模式进行验证，相当于完全信任
                    .and()
                    .withClient("springcloud-service")
                    .authorizedGrantTypes("client_credentials")
                    //加密方式同SecurityConfig配置的一致
                    .secret(new BCryptPasswordEncoder().encode("springcloud-service"));
        }

        /**
         * 必须配置authenticationManager，否则password的授权模式将不生效
         * @param endpoints
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            System.out.println("【AuthorizationServerConfig.configure(AuthorizationServerEndpointsConfigurer endpoints)】");
            endpoints
                    .accessTokenConverter(jwtAccessTokenConverter)
                    .tokenStore(tokenStore)
                    .authenticationManager(auth);
        }
    }


    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//            JwtAccessTokenConverter converter = jwtAccessTokenConverter();
//            TokenStore tokenStore = jwtTokenStore(converter);
//            resources
//                    .tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            System.out.println("【ResourceServerConfig.configure(HttpSecurity http)】");
            http
                    .authorizeRequests()
                    .antMatchers("/login", "/oauth/*")
                    .permitAll()
                    .and()
                    .authorizeRequests().anyRequest().authenticated();

        }
    }

}
