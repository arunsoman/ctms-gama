package com.gama.whiv.dto;

public class PredictedRiskDTO {
    private String customerId;
    private Double predictedRiskScore;
    private String riskFactors; // E.g., "Geographic Risk, High Transaction Volume"
    private String predictionDetails;

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getPredictedRiskScore() {
        return predictedRiskScore;
    }

    public void setPredictedRiskScore(Double predictedRiskScore) {
        this.predictedRiskScore = predictedRiskScore;
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
