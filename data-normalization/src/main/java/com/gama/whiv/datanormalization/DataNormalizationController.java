package com.gama.whiv.datanormalization;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-normalization")
public class DataNormalizationController {

    // Endpoint for validating incoming data
    @PostMapping("/validate")
    public String validateData(@RequestBody DataValidationRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Endpoint for checking schema compliance
    @PostMapping("/schema-compliance")
    public String checkSchemaCompliance(@RequestBody SchemaComplianceRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Endpoint for removing duplicates
    @PostMapping("/remove-duplicates")
    public String removeDuplicates(@RequestBody DuplicateRemovalRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Endpoint for viewing invalid data logs
    @GetMapping("/invalid-data-logs")
    public String getInvalidDataLogs(@RequestParam String sourceId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Endpoint for generating validation reports
    @GetMapping("/validation-report")
    public String generateValidationReport(@RequestParam String sourceId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Record for Data Validation Request
    public record DataValidationRequest(
            String sourceId,
            List<String> dataEntries
    ) {}

    // Record for Schema Compliance Request
    public record SchemaComplianceRequest(
            String schemaId,
            List<String> dataFields
    ) {}

    // Record for Duplicate Removal Request
    public record DuplicateRemovalRequest(
            String sourceId,
            List<String> dataEntries
    ) {}
}
