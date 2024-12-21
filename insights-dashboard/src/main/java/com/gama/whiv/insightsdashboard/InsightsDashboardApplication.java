package com.gama.whiv.insightsdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InsightsDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsightsDashboardApplication.class, args);
    }
}
