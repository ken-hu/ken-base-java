package com.hui.base.springboot.common.enumeration;

import com.hui.base.springboot.common.constant.ErrorConstant;
import lombok.Getter;

/**
 * <b><code>FormExceptionEnum</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/11/29 14:15.
 *
 * @author Hu weihui
 */
@Getter
public enum FormTokenExceptionEnum {
    DUPLICATE_SUBMIT("FT-001", ErrorConstant.NETWORK_ERROR, "表单重复提交"),

    ILLEGAL_SUBMIT("FT-002",ErrorConstant.NETWORK_ERROR,"非法提交表单"),

    SERVER_TOKEN_ERROR("FT-003",ErrorConstant.NETWORK_ERROR,"服务端未接收到请求"),

    UNKONW_ERROR("FT-004", ErrorConstant.NETWORK_ERROR, "表单提交未知错误");


    private String errorCode;

    private String errorType;

    private String errorMsg;

    FormTokenExceptionEnum(String errorCode, String errorType, String errorMsg) {
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.errorMsg = errorMsg;
    }
}
