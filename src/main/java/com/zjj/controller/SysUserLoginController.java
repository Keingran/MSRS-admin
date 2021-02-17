package com.zjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.RsaUtils;
import com.zjj.web.SysSmsLoginService;
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

    @Autowired
    private SysSmsLoginService sysSmsLoginService;


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

    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        return null;
    }
}
