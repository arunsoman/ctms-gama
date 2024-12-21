package com.gama.whiv.regulatoryreporting;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regulatory-reporting")
public class RegulatoryReportingController {

    @PostMapping("/generate")
    public String generateReport(@RequestBody GenerateReportRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/get/{reportId}")
    public String getReport(@PathVariable String reportId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/submit")
    public String submitReport(@RequestBody SubmitReportRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/list")
    public String listReports(@RequestParam(required = false) String status, @RequestParam(required = false) String reportType) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/delete/{reportId}")
    public String deleteReport(@PathVariable String reportId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record GenerateReportRequest(
            String reportType, // e.g., "STR", "SAR"
            String entityId,
            String entityType, // e.g., "Customer", "Transaction"
            String timeRange
    ) {}

    public record SubmitReportRequest(
            String reportId,
            String regulatorId,
            String comments
    ) {}
}
