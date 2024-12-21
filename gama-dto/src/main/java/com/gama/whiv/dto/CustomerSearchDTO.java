package com.gama.whiv.dto;

public class CustomerSearchDTO {
    private String query; // Full name, account number, etc.
    private String searchType; // Exact, Partial, Fuzzy
    private Integer limit; // Max number of results
}
