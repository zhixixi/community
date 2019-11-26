package com.huvenbbs.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.huvenbbs.community.dao"})
public class HuvenbbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuvenbbsApplication.class, args);
    }

}
