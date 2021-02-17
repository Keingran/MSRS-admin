package com.zjj.security.sms;

import com.alibaba.fastjson.JSONObject;
import com.zjj.utils.RsaUtils;
import com.zjj.utils.http.HttpUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信验证码的鉴权过滤器
 * 仿照 UsernamePasswordAuthenticationFilter 实现
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAzV7ajGcCIpaYczkA89seVkZzWg17gsGMp7llgINTJqKVzVwDeGmkklHjyT+FEUMueCJ5a4dg3d5xEz1VoYpAzQIDAQABAkBV3yKM8IlhAw0tqJkwXd+6fWzAK8EINHvmqcu6R68eCL/NOL8Hfrm7TBMEY16kp8GyNiAgXtwViyVYWG7TcZHhAiEA5+FKiWFtcO4Y4CR99B+HCYV8N+2/HjhDg1rM/qtEa+kCIQDiu6SyIWT9QuFkbD4TntuiEO287FFEONCgsNO/LHPzRQIhANRhmx19aPn4ejxB8EM65TopUv7++P+61MtozT2srHyRAiAXofVdt8td9o2luP6Tbvh3oQoWqg8ibEWaqjO39nszfQIgBWmfD+ESyhS2s8lkHnOMrMkwluMNRTRzz+Jj0tNQhpo=";

    // form表单中手机号码的字段name
    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";
    private String phoneParameter = SPRING_SECURITY_FORM_PHONE_KEY;
    private static final String codeParameter = "code";
    // 是否仅 POST 方式
    private boolean postOnly = true;

    public SmsAuthenticationFilter() {
        // 短信登录的请求 post 方式的 /user/login
        super(new AntPathRequestMatcher("/user/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            // 根据请求参数名，获取请求value
            Map<String, String> requestMap = this.obtainPhone(request);
            // 生成token
            SmsAuthenticationToken authRequest = new SmsAuthenticationToken(requestMap);
            this.setDetails(request, authRequest);
            // 调用 AuthenticationManager
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected Map<String,String> obtainPhone(HttpServletRequest request) throws IOException {
        JSONObject requestPlayLoad = HttpUtils.getRequestPlayLoad(request);
        String phone = (String) requestPlayLoad.get(phoneParameter);
        String code = (String) requestPlayLoad.get(codeParameter);
        String decryptPhone = null;
        String decryptCode = null;
        try {
            decryptPhone = RsaUtils.decryptByPrivateKey(privateKey, phone);
            decryptCode = RsaUtils.decryptByPrivateKey(privateKey, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("phone", decryptPhone);
        requestMap.put("code", decryptCode);
        return requestMap;
    }

    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public final String getPhoneParameter() {
        return phoneParameter;
    }

    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText(phoneParameter, "Phone parameter must not be empty or null");
        this.phoneParameter = phoneParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
