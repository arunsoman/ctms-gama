package com.gama.whiv.retention;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/retention-archival")
public class RetentionArchivalConfigurationController {

    @PostMapping("/configure-retention")
    public String configureRetentionPolicy(@RequestBody ConfigureRetentionPolicyRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/get-policy/{entityType}")
    public String getRetentionPolicy(@PathVariable String entityType) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/archive-now")
    public String archiveNow(@RequestBody ArchiveNowRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record ConfigureRetentionPolicyRequest(
            String entityType, // e.g., "Transaction", "Customer"
            String retentionPeriod, // e.g., "6 months", "1 year"
            String policyDescription
    ) {}

    public record ArchiveNowRequest(
            String entityType,
            String timeRange
    ) {}
}
