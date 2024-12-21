package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "compliance_data")
public class ComplianceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complianceId;
    private Long customerId;
    private String complianceType;
    private String complianceStatus;
    private String auditDetails;
    private String reviewerComments;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fiId;

    // Getters and Setters
}
