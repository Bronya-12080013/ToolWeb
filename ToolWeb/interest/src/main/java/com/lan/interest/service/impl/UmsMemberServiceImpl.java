package com.lan.interest.service.impl;

import com.lan.interest.common.api.CommonResult;
import com.lan.interest.service.RedisService;
import com.lan.interest.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<6;i++) sb.append(random.nextInt(10));
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if(StringUtils.isEmpty(authCode)){ //return (str == null || "".equals(str));
            return CommonResult.failed("验证码为空,请输入验证码");
        }
        String trueAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+telephone);
        if(authCode.equals(trueAuthCode)){
            return CommonResult.success(null,"验证成功");
        }else {
            return CommonResult.failed("验证码不正确");
        }
    }
}
