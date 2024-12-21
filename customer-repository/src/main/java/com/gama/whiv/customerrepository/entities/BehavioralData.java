package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "behavioral_data")
public class BehavioralData {
    @Id
    private Long customerId;
    private Double averageTransactionFrequency;
    private Double averageTransactionValue;
    private String preferredChannels;
    private String usualLocations;
    private String typicalTransactionHours;
    private String unusualPatterns;
    private String fiId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and Setters
}
