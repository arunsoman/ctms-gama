package com.gama.whiv.regulatoryreporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RegulatoryReportingApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegulatoryReportingApplication.class, args);
    }
}
