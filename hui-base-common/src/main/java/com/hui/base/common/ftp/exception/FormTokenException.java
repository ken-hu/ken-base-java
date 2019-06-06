package com.hui.base.common.ftp.exception;


import com.hui.base.common.ftp.enumeration.FormTokenExceptionEnum;

/**
 * <b><code>FormTokenException</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/3 15:26.
 *
 * @author Hu weihui
 */
public class FormTokenException extends RuntimeException{
    private static final long serialVersionUID = 512936007428810210L;

    private String errorCode;

    private String errorMsg;

    public FormTokenException(String errorCode,String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }


    public FormTokenException(String errorCode,String errorMsg,Throwable cause) {
        super(errorMsg,cause);
        this.errorCode = errorCode;
    }

    public FormTokenException(FormTokenExceptionEnum formTokenExceptionEnum) {
        super(formTokenExceptionEnum.getErrorMsg());
        this.errorCode = formTokenExceptionEnum.getErrorCode();
    }

    public FormTokenException(FormTokenExceptionEnum formTokenExceptionEnum,Throwable cause) {
        super(formTokenExceptionEnum.getErrorMsg(),cause);
        this.errorCode = errorCode;
    }
}
