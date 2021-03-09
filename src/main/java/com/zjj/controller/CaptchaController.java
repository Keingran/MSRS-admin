package com.zjj.controller;

import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.redis.RedisCache;
import com.zjj.utils.UUID.IdUtils;
import com.zjj.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.codec.Base64;

/**
 * 验证码操作处理
 */
@RestController
public class CaptchaController {

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Result getCode() throws IOException {
        // 生成随机字串（验证码长度4个）
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();

        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        try {
            Result result = Result.success();
            result.put("uuid", uuid);
            result.put("img", Base64.encode(stream.toByteArray()));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        } finally {
            stream.close();
        }
    }

}
