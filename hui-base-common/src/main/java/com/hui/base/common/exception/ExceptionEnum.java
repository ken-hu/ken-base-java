package com.hui.base.common.exception;

import lombok.Getter;

/**
 * <b><code>ExceptionEnum</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/1/6 1:53.
 *
 * @author HuWeihui
 */
@Getter
public enum ExceptionEnum {
    /*安全异常信息*/
    SECURITY_ERROR_USER_NOT_EXIST("SE-001", "用户不存在"),
    SECURITY_ERROR_PASSWORD_INCORRECT("SE-002", "账号或密码错误"),
    SECURITY_ERROR_FORBID_ACCESS("SE-003", "禁止访问,请登录获取accessToken"),
    SECURITY_ERROR_PERMISSION_NOT_ENOUGH("SE-004", "权限不足访问"),
    /*校验异常定义*/
    VERIFY_ERROR_USERID_IS_NULL("VE-001","校验失败，用户ID为空"),
    /*用户业务异常信息*/
    USER_ERROR_REPEAT_USER("U-001","操作失败，用户账户或手机号码已存在"),
    /*角色业无异常信息*/
    ROLE_ERROR_REPEAT_ROLE("RO-001", "操作失败角色已存在"),
    /*权限业无异常信息*/
    PERMISSION_ERROR_REPEAT_PERMISSION("PE-001","权限已存在"),
    /*用户组异常信息*/
    GROUP_ERROR_REPEAT_GROUP("GP-001","用户组已存在"),
    /*数据规则异常信息*/
    DATA_RULE_ERROR_REPEAT_RULE("RU-001","重复的规则");

    private String code;
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }}
