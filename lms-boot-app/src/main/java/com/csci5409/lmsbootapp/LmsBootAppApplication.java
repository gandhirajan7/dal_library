package com.csci5409.lmsbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class LmsBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsBootAppApplication.class, args);
    }
}
