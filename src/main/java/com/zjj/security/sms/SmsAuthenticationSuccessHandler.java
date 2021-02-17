package com.zjj.security.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.common.constant.HttpStatus;
import com.zjj.dto.SysUser;
import com.zjj.dto.model.LoginUser;
import com.zjj.enums.UserStatus;
import com.zjj.exception.BaseException;
import com.zjj.manager.AsyncManager;
import com.zjj.manager.factory.AsyncFactory;
import com.zjj.service.ISysUserService;
import com.zjj.service.impl.SysUserServiceImpl;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.ServletUtils;
import com.zjj.utils.StringUtils;
import com.zjj.web.TokenService;
import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * 短信验证码登录成功逻辑
 */
@Component
public class SmsAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    private static final Logger log = LoggerFactory.getLogger(SmsAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        String phone = sysUser.getPhone();
        // 根据phone查询数据库，是否有这个用户
        SysUser user = userService.selectUserByPhone(phone);
        if (StringUtils.isNotNull(user)) {
            // 如果用户状态为停用
            if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
                int code = HttpStatus.LOGIN_STATUS;
                String msg = StringUtils.format("对不起，您的账号：" + phone + " 已停用");
                ServletUtils.renderString(response, JSON.toJSONString(Result.error(code, msg)));
                log.info("{} 登录失败，用户：{} 已被停用", phone, phone);
                AsyncManager.me().execute(AsyncFactory.recordLoginLog(phone, Constants.LOGIN_FAIL, MessageUtils.message("user.status.blocked")));
            } else {
                // 如果用户状态为正常
                userService.updateUser(user);
                LoginUser loginUser = (LoginUser) createLoginUser(user);

                String token = tokenService.createToken(loginUser);
                String msg = MessageUtils.message("user.login.success");

                JSONObject result = userLoginResult(msg, user, token);
                ServletUtils.renderString(response, JSON.toJSONString(result));

                log.info("{} 登录成功", phone);
                AsyncManager.me().execute(AsyncFactory.recordLoginLog(phone, Constants.LOGIN_SUCCESS, msg));
            }
        } else {
            // 如果没有这个用户，则创建新用户
            userService.insertUser(createUser(phone));
            user = userService.selectUserByPhone(phone);
            LoginUser loginUser = (LoginUser) createLoginUser(user);

            String token = tokenService.createToken(loginUser);
            String msg = MessageUtils.message("user.login.success");

            JSONObject result = userLoginResult(msg, user, token);
            ServletUtils.renderString(response, JSON.toJSONString(result));

            log.info("{} 登录成功，创建新用户：{}", phone, phone);
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(phone, Constants.LOGIN_SUCCESS, msg));

        }
    }

    /**
     * 创建一个新用户
     *
     * @param phone 手机号
     * @return 新用户
     */
    public SysUser createUser(String phone) {
        SysUser sysUser = new SysUser();
        sysUser.setPhone(phone);
        return sysUser;
    }


    public UserDetails createLoginUser(SysUser user) {
        Set<String> perms = new HashSet<>();
        perms.add("*:*:*");
        return new LoginUser(user, perms);
    }

    public JSONObject userLoginResult(String msg, SysUser user, String token) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", HttpStatus.SUCCESS);
        jsonObject.put("data", user);
        jsonObject.put("msg", msg);
        jsonObject.put("token", token);
        return jsonObject;
    }
}
