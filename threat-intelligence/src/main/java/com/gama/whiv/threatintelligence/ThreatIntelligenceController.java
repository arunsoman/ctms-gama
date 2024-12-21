package com.gama.whiv.threatintelligence;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threat-intelligence")
public class ThreatIntelligenceController {

    @GetMapping("/threats")
    public String getThreatIntelligence() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/add-threat")
    public String addThreat(@RequestBody AddThreatRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/remove-threat/{threatId}")
    public String removeThreat(@PathVariable String threatId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/import-intelligence")
    public String importThreatIntelligence(@RequestBody ImportIntelligenceRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record AddThreatRequest(
            String threatName,
            String description,
            String severityLevel
    ) {}

    public record ImportIntelligenceRequest(
            String sourceName,
            String sourceType, // e.g., "API", "File"
            List<String> data
    ) {}
}
