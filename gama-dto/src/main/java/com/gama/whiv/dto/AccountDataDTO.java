package com.gama.whiv.dto;

public class AccountDataDTO {
    private Long accountId;
    private Long customerId; // Link to the customer
    private String accountNumber;
    private String accountType; // Savings, Checking, etc.
    private String accountStatus; // Active, Inactive, Dormant, Closed
    private String creationDate;
    private String closureDate;
    private Double transactionLimit; // Daily or monthly transaction limits
    private String linkedDevices; // List of frequently used devices
    private Double overdraftLimit;
    private String createdAt;
    private String updatedAt;
}
