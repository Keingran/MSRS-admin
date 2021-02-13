package com.zjj.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
        String phone = (String) authenticationToken.getPrincipal();

        // 3.调用自己写的 checkSmsCode() 方法，进行验证码校验，如果不合法，抛出 AuthenticationException 异常
        //checkSmsCode(phone);

        // 4.如果此时仍然没有异常，通过调用 loadUserByUsername(phone) 读取出数据库中的用户信息
        UserDetails user = userDetailsService.loadUserByUsername(phone);

        //if (user == null) {
        //    throw new InternalAuthenticationServiceException("无法获取用户信息");
        //}

        // 5.此时鉴权成功后，应当重新new一个拥有鉴权的 authenticationResult 并返回给 SmsAuthenticationFilter
        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }


    /**
     * 验证码校验
     *
     * @param phone 手机号
     */
    private void checkSmsCode(String phone) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCode = request.getParameter("smsCode");

        Map<String, Object> smsCode = (Map<String, Object>) request.getSession().getAttribute("smsCode");
        if (smsCode == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }

        String applyMobile = (String) smsCode.get("mobile");
        int code = (int) smsCode.get("code");

        if (!applyMobile.equals(phone)) {
            throw new BadCredentialsException("申请的手机号码与登录手机号码不一致");
        }
        if (code != Integer.parseInt(inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }
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
}
