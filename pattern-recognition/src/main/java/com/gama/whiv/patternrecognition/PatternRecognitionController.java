package com.gama.whiv.patternrecognition;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pattern-recognition")
public class PatternRecognitionController {

    @PostMapping("/analyze")
    public String analyzePatterns(@RequestBody AnalyzePatternsRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/get-patterns")
    public String getRecognizedPatterns() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/define-pattern")
    public String defineNewPattern(@RequestBody DefinePatternRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/delete-pattern/{patternId}")
    public String deletePattern(@PathVariable String patternId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/simulate")
    public String simulatePatternDetection(@RequestParam String simulationId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record AnalyzePatternsRequest(
            String entityId,
            String entityType, // e.g., "Customer", "Transaction"
            List<String> attributes,
            String timeRange,
            String location
    ) {}

    public record DefinePatternRequest(
            String patternName,
            String description,
            List<String> attributes,
            String condition
    ) {}
}
