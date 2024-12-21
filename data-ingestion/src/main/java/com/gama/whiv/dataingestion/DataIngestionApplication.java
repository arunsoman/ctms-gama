package com.gama.whiv.dataingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataIngestionApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataIngestionApplication.class, args);
    }
}
