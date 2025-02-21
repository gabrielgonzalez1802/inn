package com.inn.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsvcOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcOrdersApplication.class, args);
    }
}