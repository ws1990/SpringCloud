package com.ws.springcloud.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 
 * 日志注解，使用于controller方法
 * @author 王松
 * @date 2018/2/9 15:32
 * @version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 模块。作用于类
     * 如果方法设置了该值，则覆盖类的值
     * @return
     */
    String module() default "";

    /**
     * 菜单。作用于方法
     * @return
     */
    String menu() default "";

    /**
     * 描述。作用于方法
     * @return
     */
    String description() default "";

}
