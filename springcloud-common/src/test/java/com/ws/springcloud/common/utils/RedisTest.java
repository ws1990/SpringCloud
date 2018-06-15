package com.ws.springcloud.common.utils;

import com.ws.springcloud.common.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.Assert.*;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/6/14 15:46
 * @version 1.0
 */
public class RedisTest extends AbstractTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void setTest() {
        String expectValue = "value";
        stringRedisTemplate.boundValueOps("test").set(expectValue);
        String actValue = stringRedisTemplate.boundValueOps("test").get();

        assertEquals(expectValue, actValue);
    }

    @Test
    public void setExpireTimeTest() {
        String expectValue = "value";
        stringRedisTemplate.boundValueOps("testTime").set(expectValue);
        long expireTime = stringRedisTemplate.boundValueOps("testTime").getExpire();
        System.out.println(expireTime);
    }

    @Test
    public void setExpireTimeTest2() {
        String expectValue = "value";
        redisTemplate.boundValueOps("testTime2").set(expectValue);
        long expireTime = stringRedisTemplate.boundValueOps("testTime2").getExpire();
        System.out.println(expireTime);
    }

}
