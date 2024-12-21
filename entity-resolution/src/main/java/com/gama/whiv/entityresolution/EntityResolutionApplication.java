package com.gama.whiv.entityresolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EntityResolutionApplication {
    public static void main(String[] args) {
        SpringApplication.run(EntityResolutionApplication.class, args);
    }
}
