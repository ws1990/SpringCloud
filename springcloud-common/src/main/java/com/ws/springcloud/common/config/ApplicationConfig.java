package com.ws.springcloud.common.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @description 
 * 读取自定义yml文件的工具类。
 * 暂不考虑需要注意初始化的顺序，当出现问题的时候再来深究这个问题
 * @author 王松
 * @date 2017/12/25 11:06
 * @version 1.0
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application-redis.yml"));
        pspc.setProperties(yaml.getObject());
        return pspc;
    }

}
