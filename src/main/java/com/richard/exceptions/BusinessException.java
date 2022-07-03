package com.richard.exceptions;

import com.richard.enums.ResultCode;

/**
 * @author Richard
 * @version 1.0
 * @description 业务异常
 * @date 2021/8/4 下午 4:11
 */
public class BusinessException {

    private ResultCode resultCode;

    /**
     * 字段为空
     */
    public static class FieldIsNullOrEmpty extends BaseException {

        private static final long serialVersionUID = -1110330636015995722L;

        public FieldIsNullOrEmpty(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public FieldIsNullOrEmpty(String msg) {
            super(ResultCode.PARAM_IS_BLANK.getCode(), msg);
        }
    }

    /**
     * 字段值有误
     */
    public static class FieldValueIsWrong extends BaseException {

        private static final long serialVersionUID = -341601510249625468L;

        public FieldValueIsWrong(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public FieldValueIsWrong(String msg) {
            super(ResultCode.PARAM_IS_INVALID.getCode(), msg);
        }
    }


    /**
     * 应有字段不存在
     */
    public static class FieldShouldExists extends BaseException {

        private static final long serialVersionUID = -7380374171802734617L;

        public FieldShouldExists(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public FieldShouldExists(String msg) {
            super(ResultCode.PARAM_NOT_COMPLETE.getCode(), msg);
        }
    }

    /**
     * Token获取失败
     */
    public static class SecurityCheckFailed extends BaseException {

        private static final long serialVersionUID = -3014608762142410542L;

        public SecurityCheckFailed(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public SecurityCheckFailed(String msg) {
            super(ResultCode.TOKEN_GET_FAILED.getCode(), msg);
        }
    }

    /**
     * Token获取失败
     */
    public static class OutterInvokeFailed extends BaseException {

        private static final long serialVersionUID = -4964960624460169791L;

        public OutterInvokeFailed(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public OutterInvokeFailed(String msg) {
            super(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR.getCode(), msg);
        }
    }

    /**
     * 业务出现问题
     */
    public static class BusinessProblem extends BaseException {

        private static final long serialVersionUID = -4964960624460169791L;

        public BusinessProblem(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public BusinessProblem(String msg) {
            super(ResultCode.BUSINESS_PROBLEM.getCode(), msg);
        }
    }
}
