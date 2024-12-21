package com.gama.whiv.customerrepository.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
@Entity
@Table(name = "external_data")
public class ExternalData {
    @Id
    private Long customerId;
    private Boolean sanctionsListMatch;
    private Boolean watchlistMatch;
    private String adverseMediaLinks;
    private String thirdPartyRiskDetails;
    private String fiId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and Setters
}
