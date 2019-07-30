
package com.hui.base.common.handler;

import com.hui.base.common.exception.BussinessException;
import com.hui.base.common.response.ResponseWrapper;
import com.hui.base.common.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
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
@Order(0)
public class GobalExceptionHandler {

    /**
     * 校验异常处理. 400
     * 配合Hibernate validate 校验Controller传入的BEAN参数
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO bindResult3ExceptionHandler(MethodArgumentNotValidException e) {
        String message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        log.debug("controller params validate exception ", e);
        return ResponseWrapper.illegalParams(message);
    }


    /**
     * 校验异常处理. 400
     * 配合Hibernate validate 校验GET方法Controller传入的参数
     * @param e the exception result
     * @return the response model
     * @author : Hu weihui
     * @since hui_project v1
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO bindResult2ExceptionHandler(ConstraintViolationException e) {
        String message = ((ConstraintViolationException) e).getConstraintViolations().iterator().next().getMessage();
        log.debug("controller params validate exception ", e);
        return ResponseWrapper.illegalParams(message);
    }

    /**
     * 校验异常处理. 400
     * 没有开启bindResult切面的时候可以使用这个，但是controller还有重复代码
     * @param e the exception result
     * @return the response model
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
        log.debug("controller params validate exception ", e);
        return ResponseWrapper.illegalParams(errorStr.toString());
    }


    /**
     * 全局异常捕捉处理 500
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BussinessException.class)
    public ResultVO bussinesErrorHandler(BussinessException e) {
        log.error("got a BussinessException ", e);
        return ResponseWrapper.error(e.getMessage());
    }


    /**
     * 全局异常捕捉处理 500
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResultVO errorHandler(Exception e) {
        log.error("got a unkonw exception ", e);
        return ResponseWrapper.error(e.getMessage());
    }


}
