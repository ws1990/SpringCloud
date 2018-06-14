package com.ws.springcloud.auth.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ws.springcloud.common.annotation.BaseService;
import com.ws.springcloud.common.base.BaseServiceImpl;
import com.ws.springcloud.auth.server.dao.mapper.RoleMapper;
import com.ws.springcloud.auth.server.dao.model.Role;
import com.ws.springcloud.auth.server.dao.model.RoleExample;
import com.ws.springcloud.auth.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RoleService实现
 *
 * @author 王松
 * @version 1.0.0
 * @date 2018/6/14
 */
@Service
@Transactional
@BaseService
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, RoleExample> implements RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    /**
     * 抑制告警，因为该属性通过ApplicationContextListener初始化
     */
    @SuppressWarnings("all")
    @Autowired
    private RoleMapper roleMapper;

}