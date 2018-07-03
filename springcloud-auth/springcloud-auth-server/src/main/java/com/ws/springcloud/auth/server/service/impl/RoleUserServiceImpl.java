package com.ws.springcloud.auth.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ws.springcloud.common.annotation.BaseService;
import com.ws.springcloud.common.base.BaseServiceImpl;
import com.ws.springcloud.auth.server.dao.mapper.RoleUserMapper;
import com.ws.springcloud.auth.server.dao.model.RoleUser;
import com.ws.springcloud.auth.server.dao.model.RoleUserExample;
import com.ws.springcloud.auth.server.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RoleUserService实现
 *
 * @author 王松
 * @version 1.0.0
 * @date 2018/7/3
 */
@Service
@Transactional
@BaseService
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUserMapper, RoleUser, RoleUserExample> implements RoleUserService {

    private static Logger logger = LoggerFactory.getLogger(RoleUserServiceImpl.class);

    /**
     * 抑制告警，因为该属性通过ApplicationContextListener初始化
     */
    @SuppressWarnings("all")
    @Autowired
    private RoleUserMapper roleUserMapper;

}