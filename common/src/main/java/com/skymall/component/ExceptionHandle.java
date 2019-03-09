package com.skymall.component;

import com.skymall.exception.ApiRRException;
import com.skymall.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局的异常处理器，主要是返回ErrorResult到客户端
 */
@ControllerAdvice
public class ExceptionHandle {
    private static  final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response handle(Exception e){
        if(e instanceof ApiRRException){
            ApiRRException exception =(ApiRRException)e;
            logger.error("捕获到异常，code:{}, msg:{}", exception.getErrno(), exception.getMessage());
            return (Response) Response.error(exception.getErrno(), exception.getMessage());
        }else{
            logger.error("系统异常", e);
            return Response.error(e.getMessage());
        }
    }


}
