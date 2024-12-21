package com.gama.whiv.onboarding.fi.activity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fi_activity_log")
@Getter
@Setter
public class FIActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fiId;
    private String activityType;
    private String details;
    private Timestamp timestamp;
    private String performedBy;

    // Getters and Setters
}
