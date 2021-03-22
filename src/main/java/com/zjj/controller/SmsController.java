package com.zjj.controller;

import com.zjj.common.Result;
import com.zjj.common.annotation.RequestRateLimit;
import com.zjj.common.constant.Constants;
import com.zjj.common.constant.HttpStatus;
import com.zjj.common.enums.RateLimitEnum;
import com.zjj.redis.RedisCache;
import com.zjj.service.SendSms;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin  // 跨域支持
public class SmsController {
    private static final Logger log = LoggerFactory.getLogger(SysNoticeController.class);

    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisCache redisCache;


    /**
     * 发送短信验证码
     *
     * @param phone   手机号
     * @param smsType 短信类型
     * @return 发送结果
     */
    @RequestRateLimit(limit = RateLimitEnum.RRLimit_1_60)
    @GetMapping("/smsCode")
    public Result getSmsCode(@RequestParam("phone") String phone, @RequestParam("smsType") String smsType) {
        // Redis手机号短信验证码标识
        String smsKey = Constants.SMS_CODE_KEY + phone;
        // 首先根据手机号 smsKey 判断redis中是否有当前手机号的记录，没有则说明可以发送短信
        String code = redisCache.getCacheObject(smsKey);
        if (StringUtils.isNotEmpty(code)) {
            Long expireTime = redisCache.getExpire(smsKey);
            return Result.error(HttpStatus.SMS_ERROR, MessageUtils.message("user.sms.expire.time", expireTime));
        }
        log.info("[SmsController --> getSmsCode] 短信参数: {}", phone + "_" + smsType);
        code = String.valueOf(Math.round((Math.random() + new Random().nextInt(9) + 1) * 100000));
        String[] param = {code, Integer.toString(Constants.SMS_EXPIRATION)};
        // 发送验证码
        //boolean isSend = sendSms.send(phone, param);

        if (true) {
            // 设置发送短信的期限 1min发送1次
            redisCache.setCacheObject(smsKey, code, Constants.SMS_EXPIRATION, TimeUnit.MINUTES);
            Long expireTime = redisCache.getExpire(smsKey);
            return Result.success(MessageUtils.message("user.send.sms.success"), expireTime);
        } else {
            return Result.error(HttpStatus.SMS_ERROR, MessageUtils.message("user.send.sms.error"));
        }
    }

    /**
     * 发送短信验证码
     *
     * @param phone   手机号
     * @param smsType 短信类型
     * @return 发送结果
     */
    @GetMapping("/smsOrderCode")
    public Result getOrderSmsCode(@RequestParam("phone") String phone, @RequestParam("smsType") String smsType) {
        // Redis手机号短信验证码标识
        String smsKey = Constants.SMS_ORDER_CODE_KEY + phone;
        // 首先根据手机号 smsKey 判断redis中是否有当前手机号的记录，没有则说明可以发送短信
        String code = redisCache.getCacheObject(smsKey);
        if (StringUtils.isNotEmpty(code)) {
            Long expireTime = redisCache.getExpire(smsKey);
            return Result.error(HttpStatus.SMS_ERROR, MessageUtils.message("user.sms.expire.time", expireTime));
        }
        log.info("[SmsController --> getOrderSmsCode] 短信参数: {}", phone + "_" + smsType);
        code = String.valueOf(Math.round((Math.random() + new Random().nextInt(9) + 1) * 100000));
        String[] param = {code, Integer.toString(Constants.SMS_EXPIRATION)};
        // 发送验证码
        //boolean isSend = sendSms.send(phone, param);

        if (true) {
            // 设置发送短信的期限 1min发送1次
            redisCache.setCacheObject(smsKey, code, Constants.SMS_EXPIRATION, TimeUnit.MINUTES);
            Long expireTime = redisCache.getExpire(smsKey);
            return Result.success(MessageUtils.message("user.send.sms.success"), expireTime);
        } else {
            return Result.error(HttpStatus.SMS_ERROR, MessageUtils.message("user.send.sms.error"));
        }
    }
}
