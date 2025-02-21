package com.inn.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsvcClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcClientsApplication.class, args);
    }
}