package com.may54ther.dailybudget.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.may54ther.dailybudget")
@MapperScan(basePackages = "com.may54ther.dailybudget", annotationClass = Mapper.class)
public class DailyBudgetApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyBudgetApplication.class, args);
    }
}
