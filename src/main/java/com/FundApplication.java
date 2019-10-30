package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Administrator
 */
@SpringBootApplication
//开启基于注解的swagger
@EnableSwagger2
//开启异步注解功能
//@EnableAsync
//开启基于注解的定时任务
//@EnableScheduling
public class FundApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundApplication.class, args);
    }

}
