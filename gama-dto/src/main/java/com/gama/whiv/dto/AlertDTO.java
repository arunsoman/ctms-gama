package com.gama.whiv.dto;

public class AlertDTO {
    private Long alertId;
    private Long customerId;
    private String alertType; // Suspicious Transaction, KYC Mismatch, etc.
    private String alertStatus; // Open, Resolved, Escalated
    private Double alertPriority; // Numerical or categorical priority
    private String alertDetails; // Description of the alert
    private String createdAt;
    private String updatedAt;
}
