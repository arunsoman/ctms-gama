package com.gama.whiv.alertmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AlertManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlertManagementApplication.class, args);
    }
}
