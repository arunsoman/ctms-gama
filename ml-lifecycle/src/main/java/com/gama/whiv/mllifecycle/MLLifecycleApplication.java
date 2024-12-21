package com.gama.whiv.mllifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MLLifecycleApplication {
    public static void main(String[] args) {
        SpringApplication.run(MLLifecycleApplication.class, args);
    }
}
