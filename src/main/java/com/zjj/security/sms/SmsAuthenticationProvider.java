package com.zjj.security.sms;


import com.alibaba.fastjson.JSONObject;
import com.zjj.common.constant.Constants;
import com.zjj.dto.SysUser;
import com.zjj.exception.SmsException;
import com.zjj.exception.SmsExpireException;
import com.zjj.manager.AsyncManager;
import com.zjj.manager.factory.AsyncFactory;
import com.zjj.redis.RedisCache;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.ServletUtils;
import com.zjj.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 短信登陆鉴权 Provider，实现 AuthenticationProvider 接口
 */

public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;


    /**
     * 身份逻辑验证
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 1.首先将 Authentication 强转为 SmsAuthenticationToken
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;

        // 2.从 SmsAuthenticationToken 中取出登录的手机号 principal
        Map<String, String> requestMap = (Map<String, String>) authenticationToken.getPrincipal();
        String phone = requestMap.get("phone");
        String code = requestMap.get("code");
        String userInfo = phone + "$" + code;
        // 3.短信验证码校验
        // 4.如果此时仍然没有异常，通过调用 loadUserByUsername(userInfo) 读取出数据库中的用户信息
        UserDetails user = userDetailsService.loadUserByUsername(userInfo);

        SysUser user1 = createUser(phone);
        // 5.此时鉴权成功后，应当重新new一个拥有鉴权的 authenticationResult 并返回给 SmsAuthenticationFilter
        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(user1, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }


    /**
     * 根据该方法判断 AuthenticationManager 选择哪个 Provider进行认证处理
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public SysUser createUser(String phone) {
        SysUser sysUser = new SysUser();
        sysUser.setPhone(phone);
        return sysUser;
    }
}
