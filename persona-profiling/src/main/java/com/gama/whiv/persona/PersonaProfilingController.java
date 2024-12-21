package com.gama.whiv.persona;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona-profiling")
public class PersonaProfilingController {

    @GetMapping("/profiles")
    public String listProfiles() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/generate-profile")
    public String generateProfile(@RequestBody GenerateProfileRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/update-profile")
    public String updateProfile(@RequestBody UpdateProfileRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record GenerateProfileRequest(
            String entityId,
            String entityType, // e.g., "Customer", "Transaction"
            List<String> dataSources
    ) {}

    public record UpdateProfileRequest(
            String profileId,
            String updates
    ) {}
}
