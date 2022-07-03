package com.richard.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 * @author Richard
 * @since 2021-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("log_view")
@ApiModel(value = "LogRecord对象", description = "日志对象")
public class LogRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String requestId;

    private String param;

    private String begintime;

    private String endtime;

    private String result;

    private String successful;

    private String costtime;

    private String othermsg;
}
