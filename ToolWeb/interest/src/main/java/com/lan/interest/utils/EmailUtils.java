package com.lan.interest.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils implements InitializingBean {
    @Value("${QQemail.msm.mail}")
    private String email;

    @Value("${QQemail.msm.host}")
    private String host;

    @Value("${QQemail.msm.port}")
    private String port;

    @Value("${QQemail.msm.password}")
    private String password;

    public static String EMAIL;
    public static String HOST;
    public static String PORT;
    public static String PASSWORD;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.EMAIL = email;
        this.HOST = host;
        this.PORT = port;
        this.PASSWORD = password;
    }
}