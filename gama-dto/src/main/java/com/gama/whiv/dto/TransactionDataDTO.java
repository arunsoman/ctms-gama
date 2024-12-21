package com.gama.whiv.dto;

public class TransactionDataDTO {
    private Long transactionId;
    private Long customerId; // Link to the customer
    private String transactionDateTime;
    private String transactionType; // Credit, Debit, Transfer, etc.
    private Double transactionAmount;
    private String currency;
    private String senderDetails; // Could be an ID or account number
    private String receiverDetails; // Could be an ID or account number
    private String geolocation; // Origin and destination locations
    private String channel; // Mobile app, ATM, branch, etc.
    private String deviceInfo; // IMEI or browser fingerprint
    private String flaggedStatus; // Suspicious, High-Risk, etc.
    private Double riskScore;
    private String createdAt;
    private String updatedAt;
}
