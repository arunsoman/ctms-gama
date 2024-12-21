package com.gama.whiv.simulation;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulation")
public class SimulationTestingController {

    @PostMapping("/run-scenario")
    public String runSimulationScenario(@RequestBody RunScenarioRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/results/{scenarioId}")
    public String getSimulationResults(@PathVariable String scenarioId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/create-scenario")
    public String createScenario(@RequestBody CreateScenarioRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/delete-scenario/{scenarioId}")
    public String deleteScenario(@PathVariable String scenarioId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record RunScenarioRequest(
            String scenarioName,
            String timeRange,
            List<String> entities
    ) {}

    public record CreateScenarioRequest(
            String scenarioName,
            String description,
            List<String> rules
    ) {}
}
