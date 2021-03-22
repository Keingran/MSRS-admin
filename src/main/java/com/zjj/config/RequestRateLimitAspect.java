package com.zjj.config;

import com.zjj.common.annotation.RequestRateLimit;
import com.zjj.common.enums.RateLimitEnum;
import com.zjj.exception.FrequentAccessException;
import com.zjj.service.RedisService;
import com.zjj.utils.ServletUtils;
import com.zjj.utils.ip.IpUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * RequestRateLimit's aspect (only IP current limiting is implemented)
 */

@Component
@Aspect
public class RequestRateLimitAspect {

    @Autowired
    private RedisService redisService;

    @Around("@annotation(com.zjj.common.annotation.RequestRateLimit)")
    public Object requestRateLimit(ProceedingJoinPoint point) throws Throwable {
        // Gets request URI
        String requestURI = ServletUtils.getRequest().getRequestURI();
        // Gets user-agent from header
        String userAgent = ServletUtils.getRequest().getHeader("User-Agent");
        // Gets IP
        String requestIp = IpUtils.getIpAddr(ServletUtils.getRequest());

        // Gets method
        final Method method = ((MethodSignature) point.getSignature()).getMethod();
        // Gets annotation
        RequestRateLimit requestRateLimit = method.getAnnotation(RequestRateLimit.class);
        // Gets annotation params
        RateLimitEnum limitEnum = requestRateLimit.limit();
        TimeUnit timeUnit = requestRateLimit.timeUnit();
        int[] limitParams = getLimitParams(limitEnum);

        // Generates key
        String key = String.format("msrs_api_request_limit_rate_%s_%s_%s", requestIp, method.getName(), requestURI);
        // Checks
        boolean over = redisService.overRequestRateLimit(key, limitParams[0], limitParams[1], timeUnit, userAgent);
        if (over) {
            throw new FrequentAccessException();
        }
        return point.proceed();
    }

    /***
     * In the returned array,
     *
     * @return ↓
     *        elements[0] is the time limit,
     *        elements[1] is the number of times that can be requested within the time limit.
     */
    private static int[] getLimitParams(RateLimitEnum rateLimitEnum) {
        String limit = rateLimitEnum.limit();
        int[] result = new int[2];
        String[] limits = limit.split("/");
        // 规定时间访问次数
        result[0] = Integer.parseInt(limits[1]);
        // 规定时间
        result[1] = Integer.parseInt(limits[0]);
        return result;
    }
}
