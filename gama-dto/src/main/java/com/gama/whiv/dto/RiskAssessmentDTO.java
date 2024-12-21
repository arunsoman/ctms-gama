package com.gama.whiv.dto;

public class RiskAssessmentDTO {
    private Long customerId;
    private Double overallRiskScore;
    private Double geographicRiskScore;
    private Double transactionalRiskScore;
    private Double relationshipRiskScore;
    private Boolean sanctionsMatch; // True if matched to a sanctions list
    private Boolean pepMatch; // True if matched to a Politically Exposed Person
    private String adverseMediaDetails; // Links or summaries of adverse media
    private String createdAt;
    private String updatedAt;
}
