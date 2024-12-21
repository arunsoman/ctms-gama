package com.gama.whiv.temporalanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TemporalAnalysisApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemporalAnalysisApplication.class, args);
    }
}
