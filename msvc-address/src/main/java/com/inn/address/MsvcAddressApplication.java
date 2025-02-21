package com.inn.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsvcAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcAddressApplication.class, args);
    }
}