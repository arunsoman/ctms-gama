package com.gama.whiv.dto;

public class PredictedEntityDTO {
    private String entityId;
    private Double riskScore;
    private String riskFactors;
    private String predictionDetails;

    // Getters and Setters
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Double riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskFactors() {
        return riskFactors;
    }

    public void setRiskFactors(String riskFactors) {
        this.riskFactors = riskFactors;
    }

    public String getPredictionDetails() {
        return predictionDetails;
    }

    public void setPredictionDetails(String predictionDetails) {
        this.predictionDetails = predictionDetails;
    }
}
