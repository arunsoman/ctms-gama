package com.gama.whiv.policy;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/policy-parameters")
public class PolicyParameterManagementController {

    @PostMapping("/add-policy")
    public String addPolicy(@RequestBody AddPolicyRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PutMapping("/update-policy")
    public String updatePolicy(@RequestBody UpdatePolicyRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/delete-policy/{policyId}")
    public String deletePolicy(@PathVariable String policyId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/list-policies")
    public String listPolicies() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record AddPolicyRequest(
            String policyName,
            String description,
            String parameters
    ) {}

    public record UpdatePolicyRequest(
            String policyId,
            String policyName,
            String updatedParameters
    ) {}
}
