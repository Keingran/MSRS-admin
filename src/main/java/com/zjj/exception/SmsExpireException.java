package com.zjj.exception;

/**
 * 短信验证码失效异常类
 */
public class SmsExpireException extends UserException {
    private static final long serialVersionUID = 1L;

    public SmsExpireException() {
        super("user.sms.expire", null);
    }
}
