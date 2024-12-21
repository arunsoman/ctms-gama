package com.gama.whiv.dataingestion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-ingestion")
public class DataIngestionController {

    // Endpoint for batch uploads
    @PostMapping("/batch-upload")
    public String handleBatchUpload(@RequestBody BatchSubmissionRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Endpoint for real-time transaction processing
    @PostMapping("/real-time-transaction")
    public String processRealTimeTransaction(@RequestBody RealTimeTransactionRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Endpoint for fetching error reports
    @GetMapping("/error-report")
    public String getErrorReport(@RequestBody ErrorReportRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Endpoint for checking source status
    @GetMapping("/status/{sourceId}")
    public String getSourceStatus(@PathVariable String sourceId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Endpoint for monitoring data ingestion
    @GetMapping("/monitoring")
    public String monitorDataIngestion() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    // Record for Batch Submission Request
    public record BatchSubmissionRequest(
            String sourceId,
            List<String> fileUrls
    ) {}

    // Record for Real-Time Transaction Request
    public record RealTimeTransactionRequest(
            String transactionId,
            String sourceId,
            String payload
    ) {}

    // Record for Error Report Request
    public record ErrorReportRequest(
            String sourceId,
            String startDate,
            String endDate
    ) {}
}
