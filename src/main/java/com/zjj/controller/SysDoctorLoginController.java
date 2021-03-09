package com.zjj.controller;

import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.dto.model.LoginBody;
import com.zjj.web.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SysDoctorLoginController {

    @Autowired
    private SysLoginService loginService;

    /**
     * @param loginBody code验证码, phone手机号
     * @return 登录成功 or 失败
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody) {
        Result result = Result.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        result.put(Constants.TOKEN, token);
        return result;
    }
}
