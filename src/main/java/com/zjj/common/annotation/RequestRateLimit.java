package com.zjj.common.annotation;

import com.zjj.common.enums.RateLimitEnum;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestRateLimit {

    RateLimitEnum limit();

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
