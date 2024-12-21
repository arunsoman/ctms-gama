package com.gama.whiv.casemanagement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/case-management")
public class CaseManagementController {

    @PostMapping("/create")
    public String createCase(@RequestBody CreateCaseRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/get/{caseId}")
    public String getCaseDetails(@PathVariable String caseId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PutMapping("/update")
    public String updateCase(@RequestBody UpdateCaseRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/list")
    public String listCases(@RequestParam(required = false) String status, @RequestParam(required = false) String priority) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/close")
    public String closeCase(@RequestBody CloseCaseRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record CreateCaseRequest(
            String caseName,
            String description,
            String createdBy,
            String priority, // e.g., "High", "Medium", "Low"
            List<String> relatedEntities
    ) {}

    public record UpdateCaseRequest(
            String caseId,
            String status, // e.g., "Open", "In Progress", "Closed"
            String priority,
            String updatedBy,
            String comments
    ) {}

    public record CloseCaseRequest(
            String caseId,
            String resolutionSummary,
            String closedBy
    ) {}
}
