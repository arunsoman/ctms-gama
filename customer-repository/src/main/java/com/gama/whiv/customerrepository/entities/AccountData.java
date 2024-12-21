package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "accounts")
public class AccountData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private Long customerId;
    private String accountNumber;
    private String accountType;
    private String accountStatus;
    private Timestamp creationDate;
    private Timestamp closureDate;
    private Double transactionLimit;
    private String linkedDevices;
    private Double overdraftLimit;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fiId;

    // Getters and Setters
}
