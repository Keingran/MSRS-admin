package com.zjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.dto.SysUser;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.RsaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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


    /**
     * @param dto code验证码, mobile手机号
     * @return 登录成功 or 失败
     */
    @PostMapping("/login")
    public Result login(@RequestBody JSONObject dto) throws Exception {
        Map<String, String> param = JSONObject.parseObject(dto.toJSONString(), new TypeReference<Map<String, String>>() {
        });
        // 密码解密
        String decryptPhone = RsaUtils.decryptByPrivateKey(privateKey, param.get("phone"));
        String decryptCode = RsaUtils.decryptByPrivateKey(privateKey, param.get("code"));
        param.put("phone", decryptPhone);
        param.put("code", decryptCode);

        // 生成 userInfo

        log.info("[SysLoginController --> login] 查询参数：{}", param);

        return Result.success(MessageUtils.message("user.login.success"));
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        return null;
    }
}
