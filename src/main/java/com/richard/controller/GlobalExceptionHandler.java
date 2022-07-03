package com.richard.controller;

import com.richard.common.Result;
import com.richard.exceptions.BaseException;
import com.richard.utils.RequestOrResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Richard
 * @version 1.0
 * @description 全局异常处理类
 * @date 2021/8/4 下午 5:12
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理方法
     * @param request 请求
     * @param e       异常对象
     * @return 返回结果
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result defaultErrorHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error("【请求 ID】: {}, {}", request.getAttribute("requestID"), "【请求接口异常】: " + request.getRequestURL().toString() + ", 【执行结果】: " + e.getMessage());
        Result result = null;
        if (!(e instanceof BaseException)) {
            //说明出现未知错误
            result = Result.ProgramException();
        } else {
            result = new Result(false, ((BaseException) e).getCode(), e.getMessage());
        }
        return result;
    }
}
