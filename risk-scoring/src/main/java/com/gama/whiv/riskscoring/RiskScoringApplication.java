package com.gama.whiv.riskscoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RiskScoringApplication {
    public static void main(String[] args) {
        SpringApplication.run(RiskScoringApplication.class, args);
    }
}
