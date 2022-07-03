package com.richard.exceptions;

import com.richard.enums.ResultCode;

/**
 * @author Richard
 * @version 1.0
 * @description 系统异常
 * @date 2021/8/4 下午 5:24
 */
public class SystemException {

    private ResultCode resultCode;

    /**
     * 404异常
     */
    public static class PathNotFounded extends BaseException {

        private static final long serialVersionUID = 6334601117750448544L;

        public PathNotFounded(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public PathNotFounded(String msg) {
            super(ResultCode.INTERFACE_ADDRESS_INVALID.getCode(), msg);
        }
    }

    /**
     * 500异常
     */
    public static class ProgramException extends BaseException {

        private static final long serialVersionUID = 1653724249984898495L;

        public ProgramException(ResultCode resultCode) {
            super(resultCode.getCode(), resultCode.getMsg());
        }

        public ProgramException(String msg) {
            super(ResultCode.SYSTEM_INNER_ERROR.getCode(), msg);
        }
    }
}
