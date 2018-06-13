package com.ws.springcloud.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 *
 * @author cheng.luo
 * @version 1.0.0
 * @date 17:31 2017/7/19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {

}
