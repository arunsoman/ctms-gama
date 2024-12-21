package com.gama.whiv.onboarding.api;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "api_key")
@Getter
@Setter
public class APIKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyValue;
    private String fiId;
    private String status;
    private String generatedBy;
    private String revokedBy;
    private String revocationReason;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    // Getters and Setters
}
