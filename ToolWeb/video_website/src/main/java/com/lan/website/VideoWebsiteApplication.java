package com.lan.website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages={"com.lan.website.mbg.mapper"})
public class VideoWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoWebsiteApplication.class, args);
    }

}
