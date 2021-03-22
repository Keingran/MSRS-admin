package com.zjj.exception;

/**
 * 请求频繁异常类
 */
public class FrequentAccessException extends UserException {
    private static final long serialVersionUID = 1L;

    public FrequentAccessException() {
        super("frequent.access.error", null);
    }
}



