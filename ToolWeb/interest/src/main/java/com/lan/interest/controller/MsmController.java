package com.lan.interest.controller;

import com.alibaba.druid.util.StringUtils;
import com.lan.interest.common.api.CommonResult;
import com.lan.interest.service.MsmService;
import com.lan.interest.service.RedisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/*
注意，这里指的发送验证码
是单指从redis取出tel对应的code
然后发给email

而不包括前面一步（即对tel生成code，并存入redis),上步见UmsMemberController

============================
另外，测试时记得把redis打开！！！

============================
注意，这（sendMsm）是个GET请求
 */
@RestController
@RequestMapping("/msm")
@CrossOrigin
@Api(description = "发送邮箱验证码")
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    //    发送短信方法
    /* 写法一  注意注解@的不同 @PathVariable
    @GetMapping("send/{email}")
    public CommonResult sendMsm(@PathVariable String email,@PathVariable String tel){
     */
    //写法二 注意注解@的不同 @RequestParam
    @GetMapping("send/")
    public CommonResult sendMsm(@RequestParam("email") String email,@RequestParam("tel") String tel){


//        1.先从redis获取验证码，如果获取到直接返回
        String get_code =redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+tel);
        if(StringUtils.isEmpty(email)){
            return CommonResult.failed("邮箱为空");
        }
        if(StringUtils.isEmpty(tel)){
            return CommonResult.failed("电话为空");
        }
        if(StringUtils.isEmpty(get_code)){
            return CommonResult.failed("无验证码");
        }

        System.out.println("----------------1--------------------");
        System.out.println(get_code);
        //发送！！！
        Map<String, Object> param = new HashMap<>();
        param.put("code",get_code);
        if(msmService.send(param,email)){
            return CommonResult.success(null,"验证成功");
        }else{
            return CommonResult.failed("验证失败");
        }
    }

}