package com.gama.whiv.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@SpringBootApplication
public class FIOnboardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(FIOnboardingApplication.class, args);
    }
}
