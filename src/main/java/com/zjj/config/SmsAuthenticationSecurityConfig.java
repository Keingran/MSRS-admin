package com.zjj.config;

import com.zjj.security.sms.SmsAuthenticationProvider;
import com.zjj.security.sms.SmsAuthenticationFilter;
import com.zjj.security.sms.SmsAuthenticationFailureHandler;
import com.zjj.security.sms.SmsAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
 * 短信验证码 spring security配置
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    /**
     * 自定义用户认证逻辑
     */
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 认证成功处理类
     */

    @Autowired
    private SmsAuthenticationSuccessHandler smsAuthenticationSuccessHandler;

    /**
     * 认证失败处理类
     */
    @Autowired
    private SmsAuthenticationFailureHandler smsAuthenticationFailureHandler;


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(smsAuthenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(smsAuthenticationFailureHandler);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userDetailsService);

        httpSecurity.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
