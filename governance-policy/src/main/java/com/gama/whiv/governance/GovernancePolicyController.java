package com.gama.whiv.governance;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/governance-policy")
public class GovernancePolicyController {

    @GetMapping("/policies")
    public String listPolicies() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/add-policy")
    public String addPolicy(@RequestBody AddPolicyRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PutMapping("/update-policy")
    public String updatePolicy(@RequestBody UpdatePolicyRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/remove-policy/{policyId}")
    public String removePolicy(@PathVariable String policyId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record AddPolicyRequest(
            String policyName,
            String description,
            String ruleSet
    ) {}

    public record UpdatePolicyRequest(
            String policyId,
            String policyName,
            String description,
            String ruleSet
    ) {}
}
