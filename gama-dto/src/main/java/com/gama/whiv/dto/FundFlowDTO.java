package com.gama.whiv.dto;

import java.util.List;

public class FundFlowDTO {
    private Long transactionId;
    private String customerId;
    private List<FundFlowNodeDTO> fundFlowPath;
    private Double totalAmountTraced;

    // Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<FundFlowNodeDTO> getFundFlowPath() {
        return fundFlowPath;
    }

    public void setFundFlowPath(List<FundFlowNodeDTO> fundFlowPath) {
        this.fundFlowPath = fundFlowPath;
    }

    public Double getTotalAmountTraced() {
        return totalAmountTraced;
    }

    public void setTotalAmountTraced(Double totalAmountTraced) {
        this.totalAmountTraced = totalAmountTraced;
    }
}
