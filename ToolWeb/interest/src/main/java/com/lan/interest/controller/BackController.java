package com.lan.interest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Api(tags = "BackController", description = "跳转")
@Controller
@RequestMapping("/")
public class BackController {

    @ApiOperation("初始页")
    @GetMapping("/")
    public String i() {
        return "index";
    }

    @ApiOperation("登录页面")
    @GetMapping("/login")
    public String index() {
        System.out.println("haha");
        return "login";
    }
}
