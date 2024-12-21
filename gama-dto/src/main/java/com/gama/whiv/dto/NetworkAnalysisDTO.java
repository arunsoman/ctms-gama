package com.gama.whiv.dto;

import java.util.List;

public class NetworkAnalysisDTO {
    private String customerId;
    private List<NetworkNodeDTO> nodes;
    private List<NetworkEdgeDTO> edges;
    private String analysisDetails;

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<NetworkNodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NetworkNodeDTO> nodes) {
        this.nodes = nodes;
    }

    public List<NetworkEdgeDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<NetworkEdgeDTO> edges) {
        this.edges = edges;
    }

    public String getAnalysisDetails() {
        return analysisDetails;
    }

    public void setAnalysisDetails(String analysisDetails) {
        this.analysisDetails = analysisDetails;
    }
}
