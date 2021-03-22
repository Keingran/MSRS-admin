package com.zjj.service;

import org.springframework.lang.NonNull;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    boolean overRequestRateLimit(@NonNull String key, final int expireTime, final int max,
                                 @NonNull TimeUnit timeUnit, String userAgent);
}
