package com.ws.springcloud.auth.service;

import com.ws.springcloud.common.base.BaseService;
import com.ws.springcloud.auth.dao.model.User;
import com.ws.springcloud.auth.dao.model.UserExample;

/**
 * UserService接口
 *
 * @author 王松
 * @version 1.0.0
 * @date 2018/6/25
 */
public interface UserService extends BaseService<User, UserExample> {
    /**
     * 根据账号查找用户
     * @param account
     * @return
     */
    User findByAccount(String account);
}