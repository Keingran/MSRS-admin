package com.zjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.dto.SysUser;
import com.zjj.dto.SysUserAuth;
import com.zjj.dto.model.LoginUser;
import com.zjj.service.ISysUserService;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.RsaUtils;
import com.zjj.utils.StringUtils;
import com.zjj.web.SysSmsLoginService;
import com.zjj.web.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    private SysSmsLoginService sysSmsLoginService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private TokenService tokenService;


    /**
     * @param dto code验证码, phone手机号
     * @return 登录成功 or 失败
     */
    @PostMapping("/login")
    public Result login(@RequestBody JSONObject dto) throws Exception {
        Map<String, String> param = JSONObject.parseObject(dto.toJSONString(), new TypeReference<Map<String, String>>() {
        });
        // 解密
        String decryptPhone = RsaUtils.decryptByPrivateKey(privateKey, param.get("phone"));
        String decryptCode = RsaUtils.decryptByPrivateKey(privateKey, param.get("code"));
        param.put("phone", decryptPhone);
        param.put("code", decryptCode);
        log.info("[SysLoginController --> login] 查询参数：{}", param);
        // 生成 userInfo
        String token = sysSmsLoginService.login(param.get("phone"), param.get("code"));
        return (Result) Result.success(MessageUtils.message("user.login.success")).put(Constants.TOKEN, token);
    }

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

    @PostMapping("/insertUserAuth")
    public Result insertUserAuth(@RequestBody SysUserAuth userAuth,HttpServletRequest request) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();
        Long userId = user.getUserId();

        // 解密
        String decryptIdCard = RsaUtils.decryptByPrivateKey(privateKey, userAuth.getIdCardNo());
        System.out.println("IDCARD" + decryptIdCard);
        return Result.success(userAuth);
    }
}
