package com.hui.base.common.interceptor;

import com.google.common.cache.Cache;
import com.hui.base.common.annotations.AvoidDuplicateFormToken;
import com.hui.base.common.ftp.enumeration.FormTokenExceptionEnum;
import com.hui.base.common.ftp.exception.FormTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * <b><code>DuplicateSubmitInterceptor</code></b>
 * <p/>
 * Description: 表单重复提交拦截器(单节点，前后端分离情况)
 *            前后端分离->前端请求头传入USER_TOKEN
 *            前后端不分离->用户信息保存在Session
 * <p/>
 * <b>Creation Time:</b> 2018/12/3 14:25.
 *
 * @author Hu weihui
 */
@Slf4j
public class DuplicateSubmitInterceptor extends HandlerInterceptorAdapter {

    private static final String USER_TOKEN_KEY = "token";

    @Autowired
    private Cache<String, String> cache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();

        AvoidDuplicateFormToken annotation = method.getAnnotation(AvoidDuplicateFormToken.class);

        if (annotation != null) {
            boolean result = !isDuplicateSubmit(request);
            return result;
        }
        return super.preHandle(request, response, handler);
    }


    /**
     * 判断是否重复提交表单.
     *
     * @param request the request
     * @return the boolean
     * @author : Hu weihui
     */
    private boolean isDuplicateSubmit(HttpServletRequest request) {
        try {
            //请求头是否有token，没有则为非法提交
            String userToken = request.getHeader(USER_TOKEN_KEY);

            if (StringUtils.isEmpty(userToken)) {

                throw new FormTokenException(FormTokenExceptionEnum.ILLEGAL_SUBMIT);

            }

            String clientoken = cache.getIfPresent(userToken);
            //查看cache内是否有token，token2秒内清除，有则为重复提交
            if (null != clientoken){
                log.info("表单重复提交：用户token: {},表单token: {}", userToken);
                throw new FormTokenException(FormTokenExceptionEnum.DUPLICATE_SUBMIT);
            }else {
                //没有token则当做首次/二次提交，记录在cache
                cache.put(userToken,UUID.randomUUID().toString());
            }

        } catch (Exception e) {

            log.info("重复提交表单拦截器错误，{}", e.getMessage());

            throw new FormTokenException(FormTokenExceptionEnum.SERVER_TOKEN_ERROR);

        }

        return false;
    }
}
