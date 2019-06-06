package com.hui.base.common.exception;

import lombok.Getter;

@Getter
public class VerifyException extends SysException {
    private static final long serialVersionUID = -7801815443398998767L;

    public VerifyException(String message) {
        super(message);
    }

    public VerifyException(String code, String message) {
        super(code, message);
    }

    public VerifyException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public VerifyException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public VerifyException(ExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum, cause);
    }
}
