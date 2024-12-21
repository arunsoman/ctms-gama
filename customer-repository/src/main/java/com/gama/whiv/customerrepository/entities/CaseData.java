package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "cases")
public class CaseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;
    private Long customerId;
    private String caseType;
    private String caseStatus;
    private String evidenceDetails;
    private String investigatorComments;
    private String resolutionDetails;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fiId;

    // Getters and Setters
}
