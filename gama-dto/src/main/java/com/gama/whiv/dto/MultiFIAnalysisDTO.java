package com.gama.whiv.dto;

import java.util.List;

public class MultiFIAnalysisDTO {
    private List<FIAnalysisDTO> fiAnalysisList;
    private String globalInsights;

    // Getters and Setters
    public List<FIAnalysisDTO> getFiAnalysisList() {
        return fiAnalysisList;
    }

    public void setFiAnalysisList(List<FIAnalysisDTO> fiAnalysisList) {
        this.fiAnalysisList = fiAnalysisList;
    }

    public String getGlobalInsights() {
        return globalInsights;
    }

    public void setGlobalInsights(String globalInsights) {
        this.globalInsights = globalInsights;
    }
}
