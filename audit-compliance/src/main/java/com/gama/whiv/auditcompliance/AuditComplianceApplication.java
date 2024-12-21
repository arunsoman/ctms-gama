package com.gama.whiv.auditcompliance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuditComplianceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuditComplianceApplication.class, args);
    }
}
