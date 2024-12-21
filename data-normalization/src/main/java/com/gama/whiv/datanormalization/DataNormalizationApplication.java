package com.gama.whiv.datanormalization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataNormalizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataNormalizationApplication.class, args);
    }
}
