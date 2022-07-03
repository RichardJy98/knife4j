package com.richard.common;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.richard.enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Richard
 * @version 1.0
 * @description 统一返回信息类
 * @date 2021/8/4 下午 2:11
 */
@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Result implements Serializable {
    private static final long serialVersionUID = 3577228372698987294L;

    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    /**
     * 是否调用成功
     */
    @ApiModelProperty(value = "接口调用结果", required = true)
    private Boolean successful;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "接口调用信息", required = true)
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "接口调用返回数据")
    private Object data;

    /**
     * 返回时间
     */
    @ApiModelProperty(value = "接口调用时间", required = true)
    private String time;


    public Result() {
        this.code = 1;
        this.successful = true;
        this.msg = "新增数据成功";
        this.time = DateUtil.now();
    }

    public Result(Boolean isSuccess, Integer code, String msg) {
        this.successful = isSuccess;
        this.code = code;
        this.msg = msg;
        this.time = DateUtil.now();
    }

    public Result(Boolean isSuccess, Integer code, String msg, Object data) {
        this.successful = isSuccess;
        this.code = code;
        this.msg = msg;
        this.time = DateUtil.now();
        this.data = data;
    }

    //字段为空
    public static Result FieldIsNullOrEmpty() {
        return new Result(false, ResultCode.PARAM_IS_BLANK.getCode(), ResultCode.PARAM_IS_BLANK.getMsg());
    }

    public static Result FieldIsNullOrEmpty(String msg) {
        return new Result(false, ResultCode.PARAM_IS_BLANK.getCode(), msg);
    }

    public static Result FieldIsNullOrEmpty(String msg, Object data) {
        return new Result(false, ResultCode.PARAM_IS_BLANK.getCode(), msg, data);
    }

    //字段值有误
    public static Result FieldValueIsWrong() {
        return new Result(false, ResultCode.PARAM_IS_INVALID.getCode(), ResultCode.PARAM_IS_INVALID.getMsg());
    }

    public static Result FieldValueIsWrong(String msg) {
        return new Result(false, ResultCode.PARAM_IS_INVALID.getCode(), msg);
    }

    public static Result FieldValueIsWrong(String msg, Object data) {
        return new Result(false, ResultCode.PARAM_IS_INVALID.getCode(), msg, data);
    }

    //参数缺失
    public static Result FieldShouldExists() {
        return new Result(false, ResultCode.PARAM_NOT_COMPLETE.getCode(), ResultCode.PARAM_NOT_COMPLETE.getMsg());
    }

    public static Result FieldShouldExists(String msg) {
        return new Result(false, ResultCode.PARAM_NOT_COMPLETE.getCode(), msg);
    }

    public static Result FieldShouldExists(String msg, Object data) {
        return new Result(false, ResultCode.PARAM_NOT_COMPLETE.getCode(), msg, data);
    }

    //Token获取失败
    public static Result SecurityCheckFailed() {
        return new Result(false, ResultCode.TOKEN_GET_FAILED.getCode(), ResultCode.TOKEN_GET_FAILED.getMsg());
    }

    public static Result SecurityCheckFailed(String msg) {
        return new Result(false, ResultCode.TOKEN_GET_FAILED.getCode(), msg);
    }

    public static Result SecurityCheckFailed(String msg, Object data) {
        return new Result(false, ResultCode.TOKEN_GET_FAILED.getCode(), msg, data);
    }

    //某业务出现问题
    public static Result BusinessProblem() {
        return new Result(false, ResultCode.BUSINESS_PROBLEM.getCode(), ResultCode.BUSINESS_PROBLEM.getMsg());
    }

    public static Result BusinessProblem(String msg) {
        return new Result(false, ResultCode.BUSINESS_PROBLEM.getCode(), msg);
    }

    public static Result BusinessProblem(String msg, Object data) {
        return new Result(false, ResultCode.BUSINESS_PROBLEM.getCode(), msg, data);
    }

    //SQL执行
    public static Result BadExecution() {
        return new Result(false, ResultCode.BAD_EXECUTION.getCode(), ResultCode.BAD_EXECUTION.getMsg());
    }

    public static Result BadExecution(String msg) {
        return new Result(false, ResultCode.BAD_EXECUTION.getCode(), msg);
    }

    public static Result BadExecution(String msg, Object data) {
        return new Result(false, ResultCode.BAD_EXECUTION.getCode(), msg, data);
    }

    //找不到数据
    public static Result NoData() {
        return new Result(false, ResultCode.RESULE_DATA_NONE.getCode(), ResultCode.RESULE_DATA_NONE.getMsg());
    }

    public static Result NoData(String msg) {
        return new Result(false, ResultCode.RESULE_DATA_NONE.getCode(), msg);
    }

    public static Result NoData(String msg, Object data) {
        return new Result(false, ResultCode.RESULE_DATA_NONE.getCode(), msg, data);
    }

    //返回多条数据
    public static Result MoreData() {
        return new Result(false, ResultCode.DATA_IS_MORE_THANYOUWANT.getCode(), ResultCode.DATA_IS_MORE_THANYOUWANT.getMsg());
    }

    public static Result MoreData(String msg) {
        return new Result(false, ResultCode.DATA_IS_MORE_THANYOUWANT.getCode(), msg);
    }

    public static Result MoreData(String msg, Object data) {
        return new Result(false, ResultCode.DATA_IS_MORE_THANYOUWANT.getCode(), msg, data);
    }

    //无效参数
    public static Result InvalidParam() {
        return new Result(false, ResultCode.DATA_IS_WRONG.getCode(), ResultCode.DATA_IS_WRONG.getMsg());
    }

    public static Result InvalidParam(String msg) {
        return new Result(false, ResultCode.DATA_IS_WRONG.getCode(), msg);
    }

    public static Result InvalidParam(String msg, Object data) {
        return new Result(false, ResultCode.DATA_IS_WRONG.getCode(), msg, data);
    }

    //外部接口调用失败
    public static Result OutterInvokeFailed() {
        return new Result(false, ResultCode.INTERFACE_OUTTER_INVOKE_ERROR.getCode(), ResultCode.INTERFACE_OUTTER_INVOKE_ERROR.getMsg());
    }

    public static Result OutterInvokeFailed(String msg) {
        return new Result(false, ResultCode.INTERFACE_OUTTER_INVOKE_ERROR.getCode(), msg);
    }

    public static Result OutterInvokeFailed(String msg, Object data) {
        return new Result(false, ResultCode.INTERFACE_OUTTER_INVOKE_ERROR.getCode(), msg, data);
    }

    //404异常
    public static Result PathNotFounded() {
        return new Result(false, ResultCode.INTERFACE_ADDRESS_INVALID.getCode(), ResultCode.INTERFACE_ADDRESS_INVALID.getMsg());
    }

    public static Result PathNotFounded(String msg) {
        return new Result(false, ResultCode.INTERFACE_ADDRESS_INVALID.getCode(), msg);
    }

    public static Result PathNotFounded(String msg, Object data) {
        return new Result(false, ResultCode.INTERFACE_ADDRESS_INVALID.getCode(), msg, data);
    }

    //500异常
    public static Result ProgramException() {
        return new Result(false, ResultCode.SYSTEM_INNER_ERROR.getCode(), ResultCode.INTERFACE_ADDRESS_INVALID.getMsg());
    }

    public static Result ProgramException(String msg) {
        return new Result(false, ResultCode.SYSTEM_INNER_ERROR.getCode(), msg);
    }

    public static Result ProgramException(String msg, Object data) {
        return new Result(false, ResultCode.SYSTEM_INNER_ERROR.getCode(), msg, data);
    }
}
