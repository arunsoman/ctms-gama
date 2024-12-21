package com.gama.whiv.onboarding.api.usage;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "api_usage")
@Getter
@Setter
public class APIUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fiId;
    private String apiKeyId;
    private String requestEndpoint;
    private String requestMethod;
    private Timestamp timestamp;
    private Integer responseCode;
    private String responseMessage;
    private Integer requestPayloadSize;
    private Integer responsePayloadSize;

    // Getters and Setters
}
