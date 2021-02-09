package com.zjj.exception;

import com.zjj.utils.MessageUtils;
import com.zjj.utils.StringUtils;

/**
 * 基础异常
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String errorMessage;

    public BaseException(String module, String code, Object[] args, String errorMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.errorMessage = errorMessage;
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String errorMessage) {
        this(module, null, null, errorMessage);
    }

    public BaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public BaseException(String errorMessage) {
        this(null, null, null, errorMessage);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = errorMessage;
        }
        return message;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
