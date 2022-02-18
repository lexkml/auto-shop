package com.kamelchukov.autoshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.common", "com.kamelchukov.autoshop"})
@EnableFeignClients(basePackages = "com.common")
public class AutoShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoShopApplication.class, args);
    }
}
