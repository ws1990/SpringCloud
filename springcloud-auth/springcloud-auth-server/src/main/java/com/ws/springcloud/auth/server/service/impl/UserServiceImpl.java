package com.ws.springcloud.auth.server.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ws.springcloud.common.annotation.BaseService;
import com.ws.springcloud.common.base.BaseServiceImpl;
import com.ws.springcloud.auth.server.dao.mapper.UserMapper;
import com.ws.springcloud.auth.server.dao.model.User;
import com.ws.springcloud.auth.server.dao.model.UserExample;
import com.ws.springcloud.auth.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService实现
 *
 * @author 王松
 * @version 1.0.0
 * @date 2018/7/3
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

    @Override
    public User findByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }

        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountEqualTo(account)
                .andIsDeleteEqualTo(false);

        return selectFirstByExample(example);
    }

}