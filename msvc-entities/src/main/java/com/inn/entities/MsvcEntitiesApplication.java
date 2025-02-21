package com.inn.entities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsvcEntitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcEntitiesApplication.class, args);
    }
}