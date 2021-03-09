package com.zjj.web;

import com.zjj.common.constant.Constants;
import com.zjj.dto.SysDoctor;
import com.zjj.dto.SysUser;
import com.zjj.dto.model.DoctorBody;
import com.zjj.dto.model.LoginUser;
import com.zjj.enums.UserStatus;
import com.zjj.exception.*;
import com.zjj.manager.AsyncManager;
import com.zjj.manager.factory.AsyncFactory;
import com.zjj.redis.RedisCache;
import com.zjj.service.ISysDoctorService;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Component
public class SysLoginService {

    private static final Logger log = LoggerFactory.getLogger(SysLoginService.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysDoctorService doctorService;


    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(username, Constants.LOGIN_FAIL, MessageUtils.message("user.sms.expire")));
            throw new SmsExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(username, Constants.LOGIN_FAIL, MessageUtils.message("user.sms.error")));
            throw new SmsException();
        }

        // 用户验证
        SysDoctor user = doctorService.selectPasswordById(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new BaseException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        if (!user.getPassword().equals(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        DoctorBody doctorBody = (DoctorBody) createLoginUser(user);
        return tokenService.createToken(doctorBody);
    }


    public UserDetails createLoginUser(SysDoctor user) {
        Set<String> perms = new HashSet<>();
        perms.add("admin");
        return new DoctorBody(user, perms);
    }
}
