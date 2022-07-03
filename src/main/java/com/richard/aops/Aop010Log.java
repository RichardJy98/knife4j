package com.richard.aops;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.richard.common.Result;
import com.richard.utils.RequestOrResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Richard
 * @version 1.0
 * @description 记录所有对外接口的日志
 * @date 2021/8/4 下午 9:31
 */
@Slf4j
@Scope
@Aspect
@Order(1)
@Component
public class Aop010Log {

    /**
     * 请求头中的参数名称
     */
    public static final String START_TIME = "request_start";


    /**
     * 切入点,拦截controller包下所有后缀为controller类下的前缀为generate方法
     * @author Richard
     * @date 2021/8/4 下午 9:40
     */
//    @Pointcut("execution(public * com.uto.red.controller.*Controller.*(..)) || execution(public * com.uto.red.controller.*Api.*(..))")
//    @Pointcut("!execution(public * com.richard.controller.LogController.*(..)) && execution(public * com.richard.controller.*Controller.*(..))")
    @Pointcut("execution(public * com.richard.controller.*Controller.*(..)) " +
            "&& !execution(public * com.richard.controller.LogController.*(..)) " +
            "&& !execution(public * com.richard.controller.WebController.*(..))")
    public void pt() {
    }

    /**
     * 前置操作记录请求的信息
     * @param point 切入点
     * @author Richard
     * @date 2021/9/7 10:27
     */
    @Before("pt()")
    public void beforeLog(JoinPoint point) throws NoSuchFieldException, IllegalAccessException {
        //获取请求对象
        HttpServletRequest request = Objects.requireNonNull(RequestOrResponseUtil.getRequest());

        //设置请求ID
        String requestId = UUID.randomUUID().toString();
        request.setAttribute("requestID", requestId);
        Map<String, Object> parameterMap = RequestOrResponseUtil.getNameAndValue(point);
        //记录请求者参数
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgentUtil.parse(header);

        //记录请求日志信息
        log.info("【请求 ID】: {}, {}", requestId, "【请求 URL】: " + request.getRequestURL() + ", 【请求  IP】: " + RequestOrResponseUtil.getIp(request)
                + ",【数据请求方式】: " + request.getMethod() + ", 【请求类名】: " + point.getSignature().getDeclaringTypeName()
                + ", 【请求方法】: " + point.getSignature().getName() + ", 【业务类型】: " + point.getTarget().getClass().getField("TYPE").get("TYPE")
                + ", 【浏览器类型】: " + userAgent.getBrowser().toString()
                + ",【操作系统】: " + userAgent.getOs().toString() + ",【原始User-Agent】: " + header);
        log.info("【请求 ID】: {}, 【请求参数】: {}", requestId, JSONUtil.toJsonStr(parameterMap));
        //打印请求相关参数
        TimeInterval timer = DateUtil.timer();
        //将计时器放入到请求中
        request.setAttribute(START_TIME, timer);
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //执行AOP被代理的方法
        Object ret = pjp.proceed();
        //记录执行的结果
        Result returnResult = (Result) ret;
        log.info("【请求 ID】: {}, 【执行结果】: {}", RequestOrResponseUtil.getRequest().getAttribute("requestID"), JSONUtil.toJsonStr(returnResult));
        return ret;
    }

    @AfterReturning("pt()")
    public void afterReturning() {
        HttpServletRequest request = RequestOrResponseUtil.getRequest();
        TimeInterval start = (TimeInterval) Objects.requireNonNull(request).getAttribute(START_TIME);
        long costTime = start.intervalRestart();
        log.info("【请求 ID】: {}, 【请求耗时】: {}", request.getAttribute("requestID"), costTime + "ms");
    }
}

