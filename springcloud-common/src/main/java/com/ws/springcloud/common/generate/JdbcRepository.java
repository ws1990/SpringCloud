package com.ws.springcloud.common.generate;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.*;

/**
 * @description
 * 该类不是线程安全的，请不要在并发环境下使用
 * @author 王松
 * @date 2018/2/5 19:29
 * @version 1.0
 */
public class JdbcRepository {

    private JdbcOperations jdbcOperations;

    public JdbcRepository(String driverClassName, String url, String userName, String password) {
        DataSource dataSource = DataSourceBuilder.create()
                //如果不指定类型，那么默认使用连接池，会存在连接不能回收而最终被耗尽的问题
                .type(DriverManagerDataSource.class)
                .driverClassName(driverClassName)
                .url(url)
                .username(userName)
                .password(password)
                .build();
        this.jdbcOperations = new JdbcTemplate(dataSource);
    }


    /**
     * 查询所有表名
     *
     * @return
     */
    public List<String> selectTables(String sql) {
        return jdbcOperations.query(sql, SingleColumnRowMapper.newInstance(String.class));
    }

}