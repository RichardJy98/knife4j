package com.richard.controller;

import com.richard.common.Result;
import com.richard.exceptions.BusinessException;
import com.richard.utils.RequestOrResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jay
 * @version 1.0
 * @description TODO
 * @date 2022/4/4 23:08
 */
@Slf4j
@RestController
@Api(tags = "首页模块")
public class IndexController {

    public static final String TYPE = "测试";


    

    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public Result sayHi(@RequestParam(value = "name") String name) throws BusinessException.BusinessProblem {
        log.info("【请求 ID】: {}, 【接口逻辑】: {}", RequestOrResponseUtil.getRequest().getAttribute("requestID"), "已经执行了!");
        return new Result().setMsg("请求成功,姓名: " + name);
//        throw new BusinessException.BusinessProblem("出错");
    }
}
