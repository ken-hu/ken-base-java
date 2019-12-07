package com.hui.base.springboot.common.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * <b><code>ResultVO</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/20 22:20.
 *
 * @author HuWeihui
 */
@Data
@JsonSerialize
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 2826839811410987568L;

    /**
     * The constant SUCCESS_CODE.
     */
    public static final String SUCCESS_CODE = "200";

    /**
     * The constant SUCCESS_MSG.
     */
    public static final String SUCCESS_MSG = "SUCCESS";

    /**
     * The constant ERROR_CODE.
     */
    public static final String ERROR_CODE = "500";

    /**
     * The constant ERROR_MSG.
     */
    public static final String ERROR_MSG = "内部异常";

    /**
     * The constant ILLEGAL_PARAMS_CODE.
     */
    public static final String ILLEGAL_PARAMS_CODE = "100";

    /**
     * The constant ILLEGAL_PARAMS_MSG.
     */
    public static final String ILLEGAL_PARAMS_MSG = "非法参数";

    private String code;

    private String msg;

    private T data;

    /**
     * Instantiates a new Result vo.
     */
    public ResultVO(){
        this(SUCCESS_CODE, SUCCESS_MSG);
    }

    /**
     * Instantiates a new Result vo.
     *
     * @param code the code
     * @param msg  the msg
     */
    public ResultVO(String code,String msg){
        this(code, msg, null);
    }

    /**
     * Instantiates a new Result vo.
     *
     * @param code the code
     * @param msg  the msg
     * @param data the data
     */
    public ResultVO(String code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
