package com.xl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/xl/mapper/**")
public class SpringBootSercurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSercurityApplication.class, args);
    }

}
