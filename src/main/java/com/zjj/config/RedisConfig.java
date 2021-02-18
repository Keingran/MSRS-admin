package com.zjj.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * redis配置
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer = new FastJson2JsonRedisSerializer(Object.class);
        //Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);

        fastJson2JsonRedisSerializer.setObjectMapper(mapper);

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value采用fastJson的序列化方式
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        // hash的value采用fastJson的序列化方式
        template.setHashValueSerializer(fastJson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
