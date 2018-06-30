package com.ws.springcloud.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/6/28 16:09
 * @version 1.0
 */
@FeignClient(name = "springcloud-auth")
public interface AuthFeignTestService {

    /**
     * 测试
     * @return
     */
    @RequestMapping("/feign/test/index")
    String index();

}
