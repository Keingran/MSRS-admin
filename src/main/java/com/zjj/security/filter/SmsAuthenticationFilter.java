package com.zjj.security.filter;

import com.zjj.security.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信验证码的鉴权过滤器
 * 仿照 UsernamePasswordAuthenticationFilter 实现
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // form表单中手机号码的字段name
    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";
    private String phoneParameter = SPRING_SECURITY_FORM_PHONE_KEY;
    // 是否仅 POST 方式
    private boolean postOnly = true;

    public SmsAuthenticationFilter() {
        // 短信登录的请求 post 方式的 /user/login
        super(new AntPathRequestMatcher("/user/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            // 根据请求参数名，获取请求value
            String phone = this.obtainPhone(request);
            if (phone == null) {
                phone = "";
            }
            phone = phone.trim();
            // 生成token
            SmsAuthenticationToken authRequest = new SmsAuthenticationToken(phone);
            this.setDetails(request, authRequest);
            // 调用 AuthenticationManager
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(this.phoneParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String phoneParameter) {
        Assert.hasText(phoneParameter, "Phone parameter must not be empty or null");
        this.phoneParameter = phoneParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getPhoneParameter() {
        return this.phoneParameter;
    }
}
