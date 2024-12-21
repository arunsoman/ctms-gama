package com.gama.whiv.customerrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerRepositoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerRepositoryApplication.class, args);
    }
}
