package com.gama.whiv.ad_hoc;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ad-hoc-query")
public class AdHocQueryAnalyticsController {

    @PostMapping("/run-query")
    public String runAdHocQuery(@RequestBody RunQueryRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/query-result/{queryId}")
    public String getQueryResult(@PathVariable String queryId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record RunQueryRequest(
            String query,
            String description
    ) {}
}
