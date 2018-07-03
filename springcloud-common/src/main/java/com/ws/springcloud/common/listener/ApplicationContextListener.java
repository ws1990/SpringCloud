package com.ws.springcloud.common.listener;

import com.ws.springcloud.common.annotation.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * spring容器初始化完成事件
 *
 * @author cheng.luo
 * @version 1.0.0
 * @date 2017/7/20
 */
@Component
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info(">>>>> 初始化BaseService.initMapper() <<<<<");
        // spring初始化完毕后，通过反射调用所有使用BaseService注解的initMapper方法
        Map<String, Object> baseServices = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
        for (String key : baseServices.keySet()) {
            Object service = baseServices.get(key);
            String className = service.toString();
            className = className.substring(0, className.indexOf("@"));
            logger.info(">>>>> {}.initMapper() <<<<<", className);
            try {
                Method initMapper = service.getClass().getMethod("initMapper");
                initMapper.invoke(service);
            } catch (Exception e) {
                logger.info(">>>>> 初始化{}的initMapper方法异常 <<<<<", className);
            }
        }
    }

}
