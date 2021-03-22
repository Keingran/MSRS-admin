package com.zjj.service.impl;

import com.zjj.redis.RedisCache;
import com.zjj.service.RedisService;
import com.zjj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    private static final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private RedisCache redisCache;

    @Override
    public boolean overRequestRateLimit(String key, int expireTime, int max, TimeUnit timeUnit, String userAgent) {
        Assert.hasText(key, "redis key must not be blank");

        long count = increment(key, 1);
        long time = redisCache.getExpire(key);

        /*
         * count == 1 means that redis key is set for the first time
         */
        log.info("[RedisServiceImpl --> overRequestRateLimit] redis参数 count:{},time:{}", count, time);
        if (count == 1 || time == -1) {
            redisCache.expire(key, expireTime, timeUnit);
        }

        log.debug("msrs api request limit rate:too many requests: key={}, redis count={}, max count={}, " +
                "expire time= {} s, user-agent={} ", key, count, max, expireTime, userAgent);

        return count > max;
    }

    public Long increment(String key, long number) {
        boolean aNull = StringUtils.isNull(redisCache.getCacheObject(key));
        if (aNull) {
            redisCache.setCacheObject(key, number);
            return number;
        } else {
            long count = redisCache.getCacheObject(key);
            return count + number;
        }
    }
}
