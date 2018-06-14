package com.ws.springcloud.auth.server.service;

import com.ws.springcloud.common.base.BaseService;
import com.ws.springcloud.auth.server.dao.model.User;
import com.ws.springcloud.auth.server.dao.model.UserExample;

/**
 * UserService接口
 *
 * @author 王松
 * @version 1.0.0
 * @date 2018/6/14
 */
public interface UserService extends BaseService<User, UserExample> {

}