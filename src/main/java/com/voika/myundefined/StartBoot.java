package com.voika.myundefined;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = "com.voika.auth.dao")
@Slf4j
//@EnableFeignClients(basePackages = "com.voika.myundefined.service")
public class StartBoot {

    public static void main(String[] args) {
        log.info("=======================正在启动项目...=======================");
        SpringApplication.run(StartBoot.class,args);
        log.info("=======================项目启动成功！=======================");
    }
}
