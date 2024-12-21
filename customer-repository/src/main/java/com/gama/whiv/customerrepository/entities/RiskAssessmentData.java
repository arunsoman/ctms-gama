package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "risk_assessments")
public class RiskAssessmentData {
    @Id
    private Long customerId;
    private Double overallRiskScore;
    private Double geographicRiskScore;
    private Double transactionalRiskScore;
    private Double relationshipRiskScore;
    private Boolean sanctionsMatch;
    private Boolean pepMatch;
    private String adverseMediaDetails;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fiId;

    // Getters and Setters
}
