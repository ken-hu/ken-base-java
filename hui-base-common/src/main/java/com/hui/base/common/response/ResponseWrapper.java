package com.hui.base.common.response;

import org.springframework.util.StringUtils;

/**
 * <b><code>com.hui.base.springcloiud.core.response.ResponseWrapper</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/20 22:31.
 *
 * @author HuWeihui
 */
public class ResponseWrapper {

    /**
     * Instantiates a new Response wrapper.
     */
    private ResponseWrapper(){}

    /**
     * Ok result vo.
     *
     * @param <T> the type parameter
     * @return the result vo
     * @author HuWeihui
     * @since hui_project v1
     */
    public static <T> ResultVO<T> ok(){
        return new ResultVO<>();
    }

    /**
     * Ok result vo.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the result vo
     * @author HuWeihui
     * @since hui_project v1
     */
    public static <T> ResultVO<T>ok(T data){
        return new ResultVO<>(ResultVO.SUCCESS_CODE,ResultVO.SUCCESS_MSG,data);
    }

    /**
     * Error result vo.
     *
     * @param <T> the type parameter
     * @return the result vo
     * @author HuWeihui
     * @since hui_project v1
     */
    public static <T> ResultVO<T> error(){
        return new ResultVO<>(ResultVO.ERROR_CODE, ResultVO.ERROR_MSG);
    }

    /**
     * Error result vo.
     *
     * @param <T> the type parameter
     * @param msg the msg
     * @return the result vo
     * @author HuWeihui
     * @since hui_project v1
     */
    public static <T> ResultVO<T> error(String msg){
        return new ResultVO<>(ResultVO.ERROR_CODE, StringUtils.isEmpty(msg) ? ResultVO.ERROR_MSG : msg);
    }

    /**
     * Illegal params result vo.
     *
     * @param <T> the type parameter
     * @return the result vo
     * @author HuWeihui
     * @since hui_project v1
     */
    public static <T> ResultVO<T> illegalParams(){
        return new ResultVO<>(ResultVO.ILLEGAL_PARAMS_CODE,ResultVO.ILLEGAL_PARAMS_MSG);
    }

    /**
     * Illegal params result vo.
     *
     * @param <T> the type parameter
     * @param msg the msg
     * @return the result vo
     * @author HuWeihui
     * @since hui_project v1
     */
    public static <T> ResultVO<T> illegalParams(String msg){
        return new ResultVO<>(ResultVO.ILLEGAL_PARAMS_CODE, StringUtils.isEmpty(msg) ? ResultVO.ILLEGAL_PARAMS_MSG : msg);
    }

}
