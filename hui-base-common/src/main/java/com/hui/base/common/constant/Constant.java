package com.hui.base.common.constant;

import lombok.Getter;

/**
 * <b><code>Constant</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/4/28 16:01.
 *
 * @author Hu-Weihui
 * @since hui-cloud-platform ${PROJECT_VERSION}
 */
@Getter
public class Constant {
    public static class ErrorCode{
        public static final String ERROR_CAPTCHA = "验证码错误";

        public static final String ACCOUNT_BALANCE_INSUFFICIENT = "余额不足";

        public static final String TRANSFER_ERROR = "转账失败";
    }
}
