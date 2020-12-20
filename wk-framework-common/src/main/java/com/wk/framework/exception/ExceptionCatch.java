package com.wk.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.wk.framework.model.response.CommonCode;
import com.wk.framework.model.response.ResponseResult;
import com.wk.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionCatch {
    //日志记录
    protected static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    //immutableMap构建类
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    //immutableMap
    protected static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;

    static {
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
        builder.put(AccessDeniedException.class, CommonCode.UNAUTHORISE);
        EXCEPTIONS = builder.build();
    }

    //捕获 CustomException异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e) {
        LOGGER.error("catch exception : {}\r\nexception: ", e.getMessage(), e);
        ResultCode resultCode = e.getResultCode();
        ResponseResult responseResult = new ResponseResult(resultCode);
        return responseResult;
    }

    //捕获不可预知异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e) {
        LOGGER.error("catch exception : {}\r\nexception: ", e.getMessage(), e);

        //判断是否是已知异常
        ResultCode resultCode = EXCEPTIONS.get(e.getClass());

        ResponseResult responseResult;

        if (resultCode == null) {
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        } else {
            responseResult = new ResponseResult(resultCode);
        }

        //结果返回
        return responseResult;
    }
}