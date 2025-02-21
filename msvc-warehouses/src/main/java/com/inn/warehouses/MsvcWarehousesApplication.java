package com.inn.warehouses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsvcWarehousesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcWarehousesApplication.class, args);
    }
}