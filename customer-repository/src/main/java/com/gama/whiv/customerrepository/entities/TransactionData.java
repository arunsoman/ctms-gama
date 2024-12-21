package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "transactions")
public class TransactionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Long customerId;
    private Timestamp transactionDateTime;
    private String transactionType;
    private Double transactionAmount;
    private String currency;
    private String senderDetails;
    private String receiverDetails;
    private String geolocation;
    private String channel;
    private String deviceInfo;
    private String flaggedStatus;
    private Double riskScore;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fiId;

    // Getters and Setters
}
