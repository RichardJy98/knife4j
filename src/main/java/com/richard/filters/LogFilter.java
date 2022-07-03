package com.richard.filters;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.richard.aops.Aop010Log;
import com.richard.controller.GlobalExceptionHandler;
import com.richard.controller.IndexController;
import com.richard.utils.RequestOrResponseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard
 * @version 1.0
 * @description 过滤器
 * @date 2021/12/1 20:59
 */
public class LogFilter extends Filter<ILoggingEvent> {
    public static List<String> loggerNames;

    static {
        loggerNames = new ArrayList<>(4);
        loggerNames.add(Aop010Log.class.getName());
        loggerNames.add(IndexController.class.getName());
        loggerNames.add(GlobalExceptionHandler.class.getName());
        loggerNames.add(RequestOrResponseUtil.class.getName());
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        //只将自定义的日志记录到数据库
        if (loggerNames.contains(event.getLoggerName())) {
            return FilterReply.ACCEPT;
        }
        return FilterReply.DENY;
    }
}
