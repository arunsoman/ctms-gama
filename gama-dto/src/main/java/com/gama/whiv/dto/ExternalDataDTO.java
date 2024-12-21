package com.gama.whiv.dto;

public class ExternalDataDTO {
    private Long customerId;
    private Boolean sanctionsListMatch;
    private Boolean watchlistMatch;
    private String adverseMediaLinks; // URLs or descriptions of negative media
    private String thirdPartyRiskDetails; // Data from external risk providers
    private String createdAt;
    private String updatedAt;
}
