package com.lan.interest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages={"com/lan/interest/mbg/mapper","com/lan/interest/dao"})
public class InterestApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterestApplication.class, args);
    }

}
