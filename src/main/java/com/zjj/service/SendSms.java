package com.zjj.service;

/**
 * 短信服务 服务层
 */
public interface SendSms {

    /**
     * @param phone 手机号
     * @param params      模板参数(验证码 + 过期时间)
     * @return 发送短信结果 true=成功, false=失败
     */
    boolean send(String phone, String[] params);
}
