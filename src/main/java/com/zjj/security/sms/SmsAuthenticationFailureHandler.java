package com.zjj.security.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjj.common.Result;
import com.zjj.common.constant.HttpStatus;
import com.zjj.utils.ServletUtils;
import com.zjj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SmsAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(SmsAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.info("登陆失败,{}", e.getMessage());

        int code = HttpStatus.SMS_ERROR;
        String msg = StringUtils.format(e.getMessage());
        ServletUtils.renderString(response, JSON.toJSONString(Result.error(code, msg)));

        //response.setContentType("application/json;charset=UTF-8");
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("code", HttpStatus.SMS_ERROR);
        //jsonObject.put("msg", e.getMessage());
        //response.getWriter().write(jsonObject.toString());
    }
}
