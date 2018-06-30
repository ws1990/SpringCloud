package com.ws.springcloud.auth.service.impl;

import com.ws.springcloud.auth.dao.model.User;
import com.ws.springcloud.auth.service.UserService;
import com.ws.springcloud.common.annotation.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @description 
 * 被oauth2使用，必须添加@Primary注解，否则不会识别
 * @author 王松
 * @date 2018/6/29 9:51
 * @version 1.0
 */
@Service("userDetailsService")
@Transactional
@BaseService
@Primary
public class UserDetailServiceImpl implements UserDetailsService {
    /**密码加密类型的前缀*/
    private static final String PASSWORD_ENCODER_TYPE = "{bcrypt}";

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByAccount(username);
        if (user == null) {
            return null;
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .accountLocked(user.getIsLock())
                .accountExpired(user.getExpireTime() != null && new Date().after(user.getExpireTime()))
                .disabled(user.getIsDelete())
                .password(PASSWORD_ENCODER_TYPE + user.getPassword())
                //TODO 角色后面完善
                .roles("admin")
                .build();
    }

}
