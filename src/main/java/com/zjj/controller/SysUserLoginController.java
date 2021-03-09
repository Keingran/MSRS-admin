package com.zjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.common.constant.HttpStatus;
import com.zjj.dto.SysDoctor;
import com.zjj.dto.SysUser;
import com.zjj.dto.SysUserAuth;
import com.zjj.dto.model.DoctorBody;
import com.zjj.dto.model.LoginUser;
import com.zjj.service.ISysUserService;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.RsaUtils;
import com.zjj.utils.StringUtils;
import com.zjj.web.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录验证
 */
@RestController
@RequestMapping("/user")
public class SysUserLoginController {
    private static final Logger log = LoggerFactory.getLogger(SysUserLoginController.class);

    @Value("${rsa.privateKey}")
    private String privateKey;  // 私钥

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private TokenService tokenService;


    /**
     * 根据token获取用户信息
     *
     * @param request 请求头
     * @return 用户信息 or null
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            return Result.success(null, loginUser.getUser());
        }
        return Result.success(null, null);
    }

    /**
     * 根据token获取用户信息
     *
     * @param request 请求头
     * @return 用户信息 or null
     */
    @GetMapping("/getDoctorInfo")
    public Result getDoctorInfo(HttpServletRequest request) {
        DoctorBody doctorBody = (DoctorBody) tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(doctorBody)) {
            SysDoctor doctor = doctorBody.getDoctor();
            return Result.success(null, doctorBody.getDoctor());
        }
        return Result.success(null, null);
    }

    /**
     * 查询用户是否认证
     *
     * @param request 请求头
     * @return 认证信息 or null
     */
    @GetMapping("/getUserAuth")
    public Result getUserAuth(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();
        if (user.getIsAuth().equals("1")) {
            SysUserAuth userAuth = sysUserService.getUserAuth(user.getUserId());
            return Result.success(null, userAuth);
        }
        return Result.success(null, null);
    }

    /**
     * 新增用户实名认证
     *
     * @param userAuth 实名认证信息
     * @param request  请求
     * @return 认证成功 or null
     * @throws Exception 异常
     */
    @PostMapping("/insertUserAuth")
    public Result insertUserAuth(@RequestBody SysUserAuth userAuth, HttpServletRequest request) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();
        Long userId = user.getUserId();

        // 解密
        String decryptIdCard = RsaUtils.decryptByPrivateKey(privateKey, userAuth.getIdCardNo());
        userAuth.setUserId(userId);
        userAuth.setIdCardNo(decryptIdCard);

        log.info("[SysLoginController --> insertUserAuth] 用户认证信息：{}", userAuth);
        int row = sysUserService.insertUserAuth(userAuth);
        if (row > 0) {
            if (sysUserService.updateUserAuth(userId, userAuth.getUserName()) > 0) {
                user.setIsAuth("1");
                user.setUserName(userAuth.getUserName());
                tokenService.refreshTokenAndAuth(loginUser, user);
                return Result.success(MessageUtils.message("user.auth.success"));
            }
        }
        return Result.error(HttpStatus.AUTH_ERROR, MessageUtils.message("user.auth.error"));
    }
}
