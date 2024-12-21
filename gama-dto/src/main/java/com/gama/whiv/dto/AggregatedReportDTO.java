package com.gama.whiv.dto;

public class AggregatedReportDTO {
    private String fiId;
    private Long totalTransactions;
    private Double totalTransactionVolume;
    private Long flaggedTransactions;
    private Double averageRiskScore;
    private String reportingPeriod;

    // Getters and Setters
    public String getFiId() {
        return fiId;
    }

    public void setFiId(String fiId) {
        this.fiId = fiId;
    }

    public Long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public Double getTotalTransactionVolume() {
        return totalTransactionVolume;
    }

    public void setTotalTransactionVolume(Double totalTransactionVolume) {
        this.totalTransactionVolume = totalTransactionVolume;
    }

    public Long getFlaggedTransactions() {
        return flaggedTransactions;
    }

    public void setFlaggedTransactions(Long flaggedTransactions) {
        this.flaggedTransactions = flaggedTransactions;
    }

    public Double getAverageRiskScore() {
        return averageRiskScore;
    }

    public void setAverageRiskScore(Double averageRiskScore) {
        this.averageRiskScore = averageRiskScore;
    }

    public String getReportingPeriod() {
        return reportingPeriod;
    }

    public void setReportingPeriod(String reportingPeriod) {
        this.reportingPeriod = reportingPeriod;
    }
}
