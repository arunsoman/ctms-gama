package com.gama.whiv.dto;

public class CaseDTO {
    private Long caseId;
    private Long customerId;
    private String caseType; // Investigation, Compliance, etc.
    private String caseStatus; // Open, Closed, Escalated
    private String evidenceDetails; // Any attached evidence or comments
    private String investigatorComments;
    private String resolutionDetails;
    private String createdAt;
    private String updatedAt;
}
