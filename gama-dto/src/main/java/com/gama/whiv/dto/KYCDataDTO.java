package com.gama.whiv.dto;

public class KYCDataDTO {
    private Long customerId;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private String governmentIdType;  // e.g., Passport, National ID
    private String governmentIdNumber;
    private String taxIdNumber;
    private String biometricData; // Placeholder for encrypted biometric data
    private String primaryPhone;
    private String secondaryPhone;
    private String email;
    private String residentialAddress;
    private String businessAddress;
    private String occupation;
    private String employer;
    private String industry;
    private Double annualIncome;
    private String sourceOfFunds;  // Declared source of income
    private String createdAt;
    private String updatedAt;
}
