package com.gama.whiv.externaldataintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ExternalDataIntegrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExternalDataIntegrationApplication.class, args);
    }
}
