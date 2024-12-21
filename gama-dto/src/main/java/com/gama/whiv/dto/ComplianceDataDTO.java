package com.gama.whiv.dto;

public class ComplianceDataDTO {
    private Long complianceId;
    private Long customerId;
    private String complianceType; // SAR, STR, KYC Refresh, etc.
    private String complianceStatus; // Pending, Resolved, Escalated
    private String auditDetails; // Details of the audit
    private String reviewerComments;
    private String createdAt;
    private String updatedAt;
}
