package com.richard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Jay
 * @version 1.0
 * @description TODO
 * @date 2022/6/30 15:06
 */
@Controller
public class WebController {

    public static final String TYPE = "内容分发";

    @GetMapping("/log")
    public String log() {
        return "log";
    }

    @GetMapping("/userlog")
    public String userlog() {
        return "userlog";
    }
}
