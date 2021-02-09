package com.zjj.controller;

import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.common.constant.HttpStatus;
import com.zjj.redis.RedisCache;
import com.zjj.service.SendSms;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.StringUtils;
import com.zjj.utils.UUID.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final int SEND_TIME = 5; //过期时间

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return 发送结果
     */
    @GetMapping("/getCode/{phone}")
    public String getCode(@PathVariable("phone") String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (StringUtils.isNotEmpty(code)) {
            return "验证码未过期！";
        }
        // 生成6位数的验证码并存储到redis中
        code = String.valueOf(Math.round((Math.random() + new Random().nextInt(9) + 1) * 100000));
        String[] param = {code, Integer.toString(SEND_TIME)};
        boolean isSend = sendSms.send(phone, param);
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, SEND_TIME, TimeUnit.MINUTES);
            return "验证码发送成功!";
        } else {
            return "验证码发送失败!";
        }
    }

    /**
     * 发送短信验证码
     *
     * @param phone   手机号
     * @param smsType 短信类型
     * @return 发送结果
     */
    @GetMapping("/smsCode")
    public Result getSmsCode(@RequestParam("phone") String phone, @RequestParam("smsType") String smsType) {
        // 首先判断redis中是否有当前手机号的记录，没有则说明可以发送短信
        String code = redisCache.getCacheObject(phone);
        if (StringUtils.isNotEmpty(code)) {
            Long expireTime = redisCache.getExpire(phone);
            return Result.error(HttpStatus.SMS_ERROR, MessageUtils.message("user.sms.expire.time", expireTime));
        }
        String uuid = IdUtils.simpleUUID();
        String smsKey = Constants.SMS_CODE_KEY + uuid;
        redisCache.deleteObject(smsKey);

        log.info("短信参数为:{}", phone + "—" + smsType);
        code = String.valueOf(Math.round((Math.random() + new Random().nextInt(9) + 1) * 100000));
        String[] param = {code, Integer.toString(SEND_TIME)};
        //boolean isSend = sendSms.send(phone, param);

        if (true) {
            // 设置发送短信的期限 1min
            redisCache.setCacheObject(phone, code, 1, TimeUnit.MINUTES);
            // 设置短信验证码的期限 1min
            redisCache.setCacheObject(smsKey, code, Constants.SMS_EXPIRATION, TimeUnit.MINUTES);
            Long expireTime = redisCache.getExpire(phone);
            return Result.success(MessageUtils.message("user.send.sms.success"), expireTime);
        } else {
            return Result.error(HttpStatus.SMS_ERROR, MessageUtils.message("user.send.sms.error"));
        }
    }
}
