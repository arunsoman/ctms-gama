package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "alerts")
public class AlertData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;
    private Long customerId;
    private String alertType;
    private String alertStatus;
    private Double alertPriority;
    private String alertDetails;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fiId;

    // Getters and Setters
}
