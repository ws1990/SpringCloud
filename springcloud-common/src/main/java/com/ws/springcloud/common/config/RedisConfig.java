package com.ws.springcloud.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description 
 * 
 * @author 王松
 * @date 2018/6/14 15:38
 * @version 1.0
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**默认过期时间是30分钟*/
    private static final long DEFAULT_EXPIRE_TIME = 1800;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    public RedisProperties redisProperties() {
        return applicationConfig.bindPropertiesToTarget(RedisProperties.class, "redis",
                "classpath:application-redis.yml");
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisProperties.pool.maxIdle);
        config.setMaxWaitMillis(redisProperties.pool.maxWait);
        config.setMaxTotal(redisProperties.pool.maxActive);
        config.setMinIdle(redisProperties.pool.minIdle);
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        factory.setTimeout(redisProperties.timeout);
        factory.setHostName(redisProperties.host);
        factory.setPort(redisProperties.port);
        if (StringUtils.isNotBlank(redisProperties.password)) {
            factory.setPassword(redisProperties.password);
        }

        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<String, Object> redisTemplate, RedisProperties redisProperties) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        Long expireTime = redisProperties.expireTime;
        if (expireTime == null || expireTime <= 0) {
            expireTime = DEFAULT_EXPIRE_TIME;
        }
        //这里可以设置一个默认的过期时间 单位是秒
        redisCacheManager.setDefaultExpiration(expireTime);

        return redisCacheManager;
    }



    public static class RedisProperties {
        private String host;
        private Integer port;
        private Pool pool;
        private Integer timeout;
        private Long expireTime;
        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public Pool getPool() {
            return pool;
        }

        public void setPool(Pool pool) {
            this.pool = pool;
        }

        public Integer getTimeout() {
            return timeout;
        }

        public void setTimeout(Integer timeout) {
            this.timeout = timeout;
        }

        public Long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Long expireTime) {
            this.expireTime = expireTime;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Pool {
        private Integer maxActive;
        private Long maxWait;
        private Integer maxIdle;
        private Integer minIdle;

        public Integer getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(Integer maxActive) {
            this.maxActive = maxActive;
        }

        public Long getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(Long maxWait) {
            this.maxWait = maxWait;
        }

        public Integer getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(Integer maxIdle) {
            this.maxIdle = maxIdle;
        }

        public Integer getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(Integer minIdle) {
            this.minIdle = minIdle;
        }
    }

}
