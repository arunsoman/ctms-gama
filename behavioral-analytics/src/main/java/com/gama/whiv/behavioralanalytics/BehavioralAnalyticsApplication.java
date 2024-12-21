package com.gama.whiv.behavioralanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BehavioralAnalyticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BehavioralAnalyticsApplication.class, args);
    }
}
