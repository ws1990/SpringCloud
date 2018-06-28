package com.ws.springcloud.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ws.springcloud.common.annotation.BaseService;
import com.ws.springcloud.common.base.BaseServiceImpl;
import com.ws.springcloud.auth.dao.mapper.UserMapper;
import com.ws.springcloud.auth.dao.model.User;
import com.ws.springcloud.auth.dao.model.UserExample;
import com.ws.springcloud.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService实现
 *
 * @author 王松
 * @version 1.0.0
 * @date 2018/6/25
 */
@Service
@Transactional
@BaseService
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 抑制告警，因为该属性通过ApplicationContextListener初始化
     */
    @SuppressWarnings("all")
    @Autowired
    private UserMapper userMapper;

}