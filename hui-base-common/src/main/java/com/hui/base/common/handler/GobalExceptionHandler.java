/*
 * 广州丰石科技有限公司拥有本软件版权2018并保留所有权利。
 * Copyright 2018, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved
 */

package com.hui.base.common.handler;

import com.hui.base.common.exception.SysException;
import com.hui.base.common.response.ResultMapper;
import com.hui.base.common.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * <b><code>GobalExceptionHandler</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/10/29 14:20.
 *
 * @author huweihui
 */
@RestControllerAdvice
@Slf4j
public class GobalExceptionHandler {
    /**
     * 全局异常捕捉处理 500
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResultVO errorHandler(Exception e) {
        log.error("[GobalExceptionHandler] get a unkonw_exception {}", e);
        return ResultMapper.error(e.getMessage());
    }

    /**
     * 校验异常处理. 400
     * 没有开启bindResult切面的时候可以使用这个，但是controller还有重复代码
     * @param e the exception result
     * @return the response entity
     * @author : Hu weihui
     * @since hui_project v1
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultVO bindResultExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder errorStr = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorStr.append(fieldError.getDefaultMessage())
                    .append(",");
        }
        return ResultMapper.illegalParams(errorStr.toString());
    }

    /**
     * 自定义异常处理.
     *
     * @param e the exception result
     * @return the response entity
     * @author : Hu weihui
     * @since hui_project v1
     */
    @ExceptionHandler(value = SysException.class)
    public ResultVO validErrorHandler(SysException e) {
        log.error("[SysException] get a unkonw_exception {}", e);
        return ResultMapper.error(e.getMessage());
    }


}
