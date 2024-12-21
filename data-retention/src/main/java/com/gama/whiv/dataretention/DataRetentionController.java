package com.gama.whiv.dataretention;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data-retention")
public class DataRetentionController {

    @PostMapping("/archive")
    public String archiveData(@RequestBody ArchiveDataRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/retrieve")
    public String retrieveArchivedData(@RequestParam String archiveId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/delete/{archiveId}")
    public String deleteArchivedData(@PathVariable String archiveId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/list-archives")
    public String listArchives(@RequestParam(required = false) String entityType, @RequestParam(required = false) String timeRange) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/set-policy")
    public String setRetentionPolicy(@RequestBody SetRetentionPolicyRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record ArchiveDataRequest(
            String entityType, // e.g., "Transaction", "Customer"
            String timeRange,
            String description
    ) {}

    public record SetRetentionPolicyRequest(
            String entityType, // e.g., "Transaction", "Customer"
            String retentionPeriod, // e.g., "30 days", "1 year"
            String policyDescription
    ) {}
}
