package com.richard.exceptions;

import com.richard.enums.ResultCode;

/**
 * @author Richard
 * @version 1.0
 * @description 数据库异常
 * @date 2021/8/4 下午 3:54
 */
public class DBException {

    private ResultCode resultCode;

    /**
     * SQL执行错误
     */
    public static class BadExecution extends BaseException {
        private static final long serialVersionUID = 4964769933886954156L;

        public BadExecution(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public BadExecution(String msg) {
            super(ResultCode.BAD_EXECUTION.getCode(), msg);
        }
    }

    /**
     * 找不到数据
     */
    public static class NoData extends BaseException {
        private static final long serialVersionUID = 8777415230393628334L;

        public NoData(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public NoData(String msg) {
            super(ResultCode.RESULE_DATA_NONE.getCode(), msg);
        }
    }

    /**
     * 返回多行数据
     */
    public static class MoreData extends BaseException {
        private static final long serialVersionUID = -3987707665150073980L;

        public MoreData(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public MoreData(String msg) {
            super(ResultCode.DATA_IS_MORE_THANYOUWANT.getCode(), msg);
        }
    }

    /**
     * 无效参数错误
     */
    public static class InvalidParam extends BaseException {
        private static final long serialVersionUID = 4235225697094262603L;

        public InvalidParam(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public InvalidParam(String msg) {
            super(ResultCode.DATA_IS_WRONG.getCode(), msg);
        }
    }

}
