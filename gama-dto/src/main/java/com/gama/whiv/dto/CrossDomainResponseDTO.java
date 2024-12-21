package com.gama.whiv.dto;

import java.util.List;

public class CrossDomainResponseDTO {
    private KYCDataDTO kycData;
    private List<TransactionDataDTO> transactions;
    private List<RelationshipDataDTO> relationships;
    private RiskAssessmentDTO riskAssessment;

    // Getters and Setters
    public KYCDataDTO getKycData() {
        return kycData;
    }

    public void setKycData(KYCDataDTO kycData) {
        this.kycData = kycData;
    }

    public List<TransactionDataDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDataDTO> transactions) {
        this.transactions = transactions;
    }

    public List<RelationshipDataDTO> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RelationshipDataDTO> relationships) {
        this.relationships = relationships;
    }

    public RiskAssessmentDTO getRiskAssessment() {
        return riskAssessment;
    }

    public void setRiskAssessment(RiskAssessmentDTO riskAssessment) {
        this.riskAssessment = riskAssessment;
    }
}
