package com.zjj.web;

import com.zjj.common.constant.Constants;
import com.zjj.dto.SysUser;
import com.zjj.dto.model.LoginUser;
import com.zjj.enums.UserStatus;
import com.zjj.exception.*;
import com.zjj.manager.AsyncManager;
import com.zjj.manager.factory.AsyncFactory;
import com.zjj.redis.RedisCache;
import com.zjj.service.ISysUserService;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 */
@Component
public class SysSmsLoginService {
    private static final Logger log = LoggerFactory.getLogger(SysSmsLoginService.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param phone 手机号
     * @param code  验证码
     * @return 结果
     */
    public String login(String phone, String code) {
        //// 短信验证码的Key
        //String smsCodeKey = Constants.SMS_CODE_KEY + phone;
        //// 查询redis中是否有这个key
        //String smsCode = redisCache.getCacheObject(smsCodeKey);
        //// 如果没有这个key, 则验证码已过期
        //if (smsCode == null) {
        //    AsyncManager.me().execute(AsyncFactory.recordLoginLog(phone, Constants.LOGIN_FAIL, MessageUtils.message("user.sms.expire")));
        //    throw new SmsExpireException();
        //}
        //// 如果输入的验证码不正确
        //if (!code.equalsIgnoreCase(smsCode)) {
        //    AsyncManager.me().execute(AsyncFactory.recordLoginLog(phone, Constants.LOGIN_FAIL, MessageUtils.message("user.sms.error")));
        //    throw new SmsException();
        //}
        //
        //// 用户验证,根据 phone 查询数据库，是否有这个用户
        //SysUser user = userService.selectUserByPhone(phone);
        //String token = null;
        //if (StringUtils.isNull(user)) {
        //    LoginUser newUser = createUser(phone);
        //    SysUser newUser1 = createUse1r(phone);
        //    userService.insertUser(newUser1);
        //    log.info("新用户：{} ===> 创建新账号", phone);
        //    // 生成token
        //    token = tokenService.createToken(newUser);
        //} else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
        //    log.info("登录用户：{} 已被停用.", phone);
        //    throw new BaseException("对不起，您的账号：" + phone + " 已停用");
        //}
        //return token;
        return null;
    }


    /**
     * 创建一个新用户
     *
     * @param phone 手机号
     * @return 新用户信息
     */
    public LoginUser createUser(String phone) {
        LoginUser sysUser = new LoginUser();
        sysUser.setBrowser(phone);
        return sysUser;
    }


    /**
     * 创建一个新用户
     *
     * @param phone 手机号
     * @return 新用户信息
     */
    public SysUser createUse1r(String phone) {
        SysUser sysUser = new SysUser();
        sysUser.setPhone(phone);
        return sysUser;
    }
}
