package com.zjj.exception;

/**
 * 验证码错误异常类
 */
public class SmsException extends UserException {
    private static final long serialVersionUID = 1L;

    public SmsException() {
        super("user.sms.error", null);
    }
}
