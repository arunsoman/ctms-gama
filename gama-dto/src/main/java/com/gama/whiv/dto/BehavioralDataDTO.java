package com.gama.whiv.dto;

public class BehavioralDataDTO {
    private Long customerId;
    private Double averageTransactionFrequency;
    private Double averageTransactionValue;
    private String preferredChannels; // Mobile, Branch, etc.
    private String usualLocations; // List of geolocations
    private String typicalTransactionHours; // Time of day for transactions
    private String unusualPatterns; // Any identified behavioral anomalies
    private String createdAt;
    private String updatedAt;
}
