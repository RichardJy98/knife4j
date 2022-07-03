package com.richard.controller;

import com.richard.common.Result;
import com.richard.enums.ResultCode;
import com.richard.exceptions.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Richard
 * @version 1.0
 * @description TODO
 * @date 2021/8/4 下午 6:56
 */
@Slf4j
@Controller
public class NotFoundException implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @ResponseBody
    @RequestMapping("/error")
    public Result error() {
        SystemException.PathNotFounded pathNotFounded = new SystemException.PathNotFounded(ResultCode.INTERFACE_ADDRESS_INVALID);
        pathNotFounded.printStackTrace();
        return Result.PathNotFounded();
    }
}