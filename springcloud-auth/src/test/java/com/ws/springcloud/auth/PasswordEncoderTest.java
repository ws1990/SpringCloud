package com.ws.springcloud.auth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/6/30 10:47
 * @version 1.0
 */
public class PasswordEncoderTest {

    @Test
    public void encoderTest() {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        System.out.println(pe.encode("123456"));
    }
}
