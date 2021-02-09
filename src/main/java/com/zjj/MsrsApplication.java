package com.zjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjj.mapper")
public class MsrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsrsApplication.class, args);
    }

}
