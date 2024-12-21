package com.gama.whiv.amlknowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AMLKnowledgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AMLKnowledgeApplication.class, args);
    }
}
