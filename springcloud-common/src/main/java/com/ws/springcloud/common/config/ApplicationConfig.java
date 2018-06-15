package com.ws.springcloud.common.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.boot.env.PropertySourcesLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * @description 
 * 读取自定义yml文件的工具类
 * @author 王松
 * @date 2017/12/25 11:06
 * @version 1.0
 */
@Configuration
public class ApplicationConfig {

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    @Autowired
    private Environment environment = new StandardEnvironment();

    public <T> T bindPropertiesToTarget(Class<T> clazz, String prefix, String... locations) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T newInstance = constructor.newInstance();

            PropertiesConfigurationFactory<Object> factory = new PropertiesConfigurationFactory<>(newInstance);
            factory.setPropertySources(loadPropertySources(locations));
            factory.setConversionService(new DefaultConversionService());
            if (StringUtils.isNotBlank(prefix)) {
                factory.setTargetName(prefix);
            }
            factory.bindPropertiesToTarget();
            return newInstance;
        } catch (Exception ex) {
            String targetClass = ClassUtils.getShortName(clazz);
            throw new BeanCreationException(clazz.getSimpleName(), "Could not bind properties to " + targetClass + " (" + clazz.getSimpleName() + ")", ex);
        }
    }
    private PropertySources loadPropertySources(String[] locations) {
        try {
            PropertySourcesLoader loader = new PropertySourcesLoader();
            for (String location : locations) {
                Resource resource = this.resourceLoader.getResource(this.environment.resolvePlaceholders(location));
                String[] profiles = this.environment.getActiveProfiles();
                for (int i = profiles.length; i-- > 0;) {
                    String profile = profiles[i];
                    loader.load(resource, profile);
                }
                loader.load(resource);
            }
            return loader.getPropertySources();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
