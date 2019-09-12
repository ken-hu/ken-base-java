package com.hui.base.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <b><code>ResponseVO</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/9/12 0:15.
 *
 * @author Hu-Weihui
 */
@ToString
@Getter
@Setter
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 1052605236159056373L;

    private ResponseVO() {
    }

    public ResponseVO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVO(T data) {
        this.data = data;
    }

    public ResponseVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
    private T data;

    private static <T> ResponseVO<T> response(String code, String msg, T data) {
        return new ResponseVO<>(code, msg, data);
    }

    /**
     * OK
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> ok() {
        return ok(null);
    }

    public static <T> ResponseVO<T> ok(T data) {
        return ok(ResponseConstant.SUCCESS, data);
    }

    public static <T> ResponseVO<T> ok(String msg, T data) {
        return new ResponseVO<>(ResponseConstant.SUCCESS, msg, data);
    }


    /**
     * Error
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> error() {
        return error(null);
    }

    public static <T> ResponseVO<T> error(T data) {
        return error(ResponseConstant.ERROR, data);
    }

    public static <T> ResponseVO<T> error(String msg, T data) {
        return new ResponseVO<>(ResponseConstant.ERROR_CODE, msg, data);
    }

    /**
     * FORBIDDEN
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> forbidden() {
        return forbidden(null);
    }

    public static <T> ResponseVO<T> forbidden(T data) {
        return forbidden(ResponseConstant.FORBIDDEN, data);
    }

    public static <T> ResponseVO<T> forbidden(String msg, T data) {
        return new ResponseVO<>(ResponseConstant.FORBIDDEN_CODE, msg, data);
    }


    /**
     * UNAUTHORIZED
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> unauthorized() {
        return unauthorized(null);
    }

    public static <T> ResponseVO<T> unauthorized(T data) {
        return unauthorized(ResponseConstant.UNAUTHORIZED, data);
    }

    public static <T> ResponseVO<T> unauthorized(String msg, T data) {
        return new ResponseVO<>(ResponseConstant.UNAUTHORIZED_CODE, msg, data);
    }

    /**
     * NOT_FOUND
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> notFound() {
        return notFound(null);
    }

    public static <T> ResponseVO<T> notFound(T data) {
        return notFound(ResponseConstant.NOT_FOUND, data);
    }

    public static <T> ResponseVO<T> notFound(String msg, T data) {
        return new ResponseVO<>(ResponseConstant.NOT_FOUND_CODE, msg, data);
    }


    /**
     * BAD_REQUEST
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> badRequest() {
        return badRequest(null);
    }

    public static <T> ResponseVO<T> badRequest(T data) {
        return badRequest(ResponseConstant.BAD_REQUEST, data);
    }

    public static <T> ResponseVO<T> badRequest(String msg, T data) {
        return new ResponseVO<>(ResponseConstant.BAD_REQUEST_CODE, msg, data);
    }

    static class ResponseConstant {
        private static final String SUCCESS = "SUCCESS";
        private static final String SUCCESS_CODE = "200";

        private static final String ERROR = "ERROR";
        private static final String ERROR_CODE = "500";

        private static final String FORBIDDEN = "FORBIDDEN";
        private static final String FORBIDDEN_CODE = "403";

        private static final String UNAUTHORIZED = "UNAUTHORIZED";
        private static final String UNAUTHORIZED_CODE = "401";

        private static final String NOT_FOUND = "NOT_FOUND";
        private static final String NOT_FOUND_CODE = "404";

        private static final String BAD_REQUEST = "BAD_REQUEST";
        private static final String BAD_REQUEST_CODE = "400";
    }

}
