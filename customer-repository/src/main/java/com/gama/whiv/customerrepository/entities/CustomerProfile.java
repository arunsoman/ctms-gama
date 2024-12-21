package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.util.List;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "customer_profile")
public class CustomerProfile {
    @Id
    private Long customerId;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private Double overallRiskScore;
    private String accountStatus;
    @OneToMany(mappedBy = "customerId")
    private List<AccountData> accounts;
    @OneToMany(mappedBy = "customerId")
    private List<TransactionData> recentTransactions;
    private String sanctionsStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fiId;

    // Getters and Setters
}
