package com.richard.config;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.db.DBAppenderBase;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Richard
 * @version 1.0
 * @description: Logback数据库日志配置类
 * @date 2021/12/1 19:37
 */
@Configuration
public class MyDevDBAppender extends DBAppenderBase<ILoggingEvent> {

    private String insertSQL;
    private static final Method GET_GENERATED_KEYS_METHOD;

    private static final int TIME_INDEX = 1;
    private static final int MESSAGE_INDEX = 2;
    private static final int LEVEL_STRING_INDEX = 3;
    private static final int LOGGER_NAME_INDEX = 4;
    private static final int THREAD_NAME_INDEX = 5;
    private static final int REQUEST_ID_INDEX = 6;
    private static final int ARTCILE_INDEX = 7;
    private static final int SUCCESSFUL_INDEX = 8;
    private static final int CALLER_FILENAME_INDEX = 9;
    private static final int CALLER_CLASS_INDEX = 10;
    private static final int CALLER_METHOD_INDEX = 11;
    private static final int CALLER_LINE_INDEX = 12;

    private static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    static {
        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
        Method getGeneratedKeysMethod;
        try {
            // the
            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
        } catch (Exception ex) {
            getGeneratedKeysMethod = null;
        }
        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }

    @Override
    public void start() {
        insertSQL = buildInsertSQL();
        super.start();
    }

    private static String buildInsertSQL() {
        return "INSERT INTO log_record_test (time, message, level_string, logger_name, thread_name, request_id, artcile, successful, caller_filename, caller_class, caller_method, caller_line) VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        stmt.setTimestamp(TIME_INDEX, new Timestamp(event.getTimeStamp()));
        stmt.setString(MESSAGE_INDEX, event.getFormattedMessage());
        stmt.setString(LEVEL_STRING_INDEX, event.getLevel().toString());
        stmt.setString(LOGGER_NAME_INDEX, event.getLoggerName());
        stmt.setString(THREAD_NAME_INDEX, event.getThreadName());
        //每个日志的开头必须是请求ID
        stmt.setString(REQUEST_ID_INDEX, String.valueOf(event.getArgumentArray()[0]));
        String artcile = (String) event.getArgumentArray()[1];
        stmt.setString(ARTCILE_INDEX, artcile);

        if (artcile.contains("成功")) {
            stmt.setString(SUCCESSFUL_INDEX, "Y");
        } else {
            stmt.setString(SUCCESSFUL_INDEX, "N");
        }
    }


    private void bindCallerDataWithPreparedStatement(PreparedStatement stmt, StackTraceElement[] callerDataArray) throws SQLException {
        StackTraceElement caller = extractFirstCaller(callerDataArray);
        stmt.setString(CALLER_FILENAME_INDEX, caller.getFileName());
        stmt.setString(CALLER_CLASS_INDEX, caller.getClassName());
        stmt.setString(CALLER_METHOD_INDEX, caller.getMethodName());
        stmt.setString(CALLER_LINE_INDEX, Integer.toString(caller.getLineNumber()));
    }

    @Override
    protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {
        bindLoggingEventWithInsertStatement(insertStatement, event);
        // 将所有的自定义参数进行插入
//        bindLoggingEventArgumentsWithPreparedStatement(insertStatement, event.getArgumentArray());
        bindCallerDataWithPreparedStatement(insertStatement, event.getCallerData());
        int updateCount = insertStatement.executeUpdate();
        if (updateCount != 1) {
            addWarn("Failed to insert loggingEvent");
        }
    }

//    private void bindLoggingEventArgumentsWithPreparedStatement(PreparedStatement stmt, Object[] argArray) throws SQLException {
//
//        int arrayLen = argArray != null ? argArray.length : 0;
//
//        for (int i = 0; i < arrayLen && i < 4; i++) {
//            stmt.setString(ARG0_INDEX + i, asStringTruncatedTo254(argArray[i]));
//        }
//        if (arrayLen < 4) {
//            for (int i = arrayLen; i < 4; i++) {
//                stmt.setString(ARG0_INDEX + i, null);
//            }
//        }
//    }

    /**
     * 将传入的参数转换成254长度以内的字符串
     * @param o
     * @return java.lang.String
     * @author Jay
     * @date 2022/6/30 16:11
     */
    String asStringTruncatedTo254(Object o) {
        String s = null;
        if (o != null) {
            s = o.toString();
        }

        if (s == null) {
            return null;
        }
        if (s.length() <= 254) {
            return s;
        } else {
            return s.substring(0, 254);
        }
    }

    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
        StackTraceElement caller = EMPTY_CALLER_DATA;
        if (hasAtLeastOneNonNullElement(callerDataArray))
            caller = callerDataArray[0];
        return caller;
    }

    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
    }

    @Override
    protected Method getGeneratedKeysMethod() {
        return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
        return insertSQL;
    }

    @Override
    protected void secondarySubAppend(ILoggingEvent event, Connection connection, long eventId) throws Throwable {
    }
}