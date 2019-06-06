package com.hui.base.common.exception;

/**
 * <b><code>SysException</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/1/15 17:37.
 *
 * @author Hu weihui
 */
public class SysException extends RuntimeException{

    private static final long serialVersionUID = 6395104692140869165L;

    private String code;
    private String message;

    public SysException(String message) {
        super(message);
        this.message = message;
    }

    public SysException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SysException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public SysException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public SysException(ExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum.getMessage(), cause);
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }
}
