package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "kyc")
public class KYCData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private String governmentIdType;
    private String governmentIdNumber;
    private String taxIdNumber;
    private String biometricData;
    private String primaryPhone;
    private String secondaryPhone;
    private String email;
    private String residentialAddress;
    private String businessAddress;
    private String occupation;
    private String employer;
    private String industry;
    private Double annualIncome;
    private String sourceOfFunds;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fiId;

}
