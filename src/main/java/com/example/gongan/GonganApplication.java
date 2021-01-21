package com.example.gongan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class GonganApplication {

    public static void main(String[] args) {

        SpringApplication.run(GonganApplication.class, args);
        System.out.println("程序启动成功");
    }
}
