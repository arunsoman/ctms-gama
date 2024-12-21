package com.gama.whiv.mllifecycle;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ml-models")
public class MLModelLifecycleController {

    @PostMapping("/deploy")
    public String deployModel(@RequestBody DeployModelRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PutMapping("/update")
    public String updateModel(@RequestBody UpdateModelRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/delete/{modelId}")
    public String deleteModel(@PathVariable String modelId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/get/{modelId}")
    public String getModelDetails(@PathVariable String modelId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/list")
    public String listModels(@RequestParam(required = false) String status, @RequestParam(required = false) String type) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record DeployModelRequest(
            String modelName,
            String version,
            String description,
            String modelFilePath,
            List<String> applicableUseCases
    ) {}

    public record UpdateModelRequest(
            String modelId,
            String modelName,
            String version,
            String description,
            List<String> updatedUseCases
    ) {}
}
