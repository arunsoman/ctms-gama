package com.gama.whiv.bigdata;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/big-data")
public class BigDataProcessingController {

    @PostMapping("/process-batch")
    public String processBatch(@RequestBody ProcessBatchRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/process-stream")
    public String processStream(@RequestBody ProcessStreamRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/jobs")
    public String listProcessingJobs(@RequestParam(required = false) String status) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record ProcessBatchRequest(
            String batchId,
            String source,
            String processingType
    ) {}

    public record ProcessStreamRequest(
            String streamId,
            String source,
            String processingType
    ) {}
}
