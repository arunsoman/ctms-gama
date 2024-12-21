package com.gama.whiv.dto;

import java.util.List;

public class CustomerProfileDTO {
    private Long customerId;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private Double overallRiskScore;
    private String accountStatus; // Consolidated account status (Active, Dormant, etc.)
    private List<AccountDataDTO> accounts; // List of accounts linked to the customer
    private List<TransactionDataDTO> recentTransactions; // Recent transactions for quick view
    private String sanctionsStatus; // Matched or Not Matched
    private String createdAt;
    private String updatedAt;
}
