package com.zjj.web;

import com.zjj.common.constant.Constants;
import com.zjj.dto.SysUser;
import com.zjj.manager.AsyncManager;
import com.zjj.manager.factory.AsyncFactory;
import com.zjj.redis.RedisCache;
import com.zjj.service.ISysUserService;
import com.zjj.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private RedisCache redisCache;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] sList = username.split("\\$");
        String phone = sList[0];
        String code = sList[1];
        checkSmsCode(phone, code);
        return null;
    }

    /**
     * 验证码校验
     *
     * @param phone 手机号
     */
    private void checkSmsCode(String phone, String code) {
        // 短信验证码的Key
        String smsCodeKey = Constants.SMS_CODE_KEY + phone;
        // 查询redis中是否有这个key
        String smsCode = redisCache.getCacheObject(smsCodeKey);
        // 如果没有这个key, 则验证码已过期
        if (smsCode == null) {
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(phone, Constants.LOGIN_FAIL, MessageUtils.message("user.sms.expire")));
            //throw new SmsExpireException();
            throw new BadCredentialsException(MessageUtils.message("user.sms.expire"));
        }
        // 如果输入的验证码不正确
        if (!code.equalsIgnoreCase(smsCode)) {
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(phone, Constants.LOGIN_FAIL, MessageUtils.message("user.sms.error")));
            //throw new SmsException();
            throw new BadCredentialsException(MessageUtils.message("user.sms.error"));
        }
    }
}
