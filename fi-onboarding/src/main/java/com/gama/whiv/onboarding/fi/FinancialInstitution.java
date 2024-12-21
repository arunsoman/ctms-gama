package com.gama.whiv.onboarding.fi;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "financial_institution")
@Getter
@Setter
public class FinancialInstitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fiName;
    private String fiDescription;
    private String registrationNumber;
    private String licenseDetails;
    private String contactPersonName;
    private String contactDesignation;
    private String contactEmail;
    private String contactPhone;
    private String secondaryContactDetails;
    private String callbackURL;
    private Set<String> ipWhitelist;
    private String apiAccessType;
    private String encryptionPublicKey;
    private String dataSubmissionFrequency;
    private String supportedDataFormats;
    private String onboardingDate;
    private String regionOfOperation;
    private String amlComplianceOfficer;
    private String kycPolicyAdherence;
    private String complianceCertification;
    private String onboardingStatus;
    private String remarks;
    private String businessType;
    private String businessCategory;
    private String businessSubCategory;
    private String businessDescription;
    private String website;
    private String socialMediaHandles;
    private String customerSupportEmail;
    private String customerSupportPhone;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String updatedBy;

    // Getters and Setters
}
