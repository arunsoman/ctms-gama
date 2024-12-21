package com.gama.whiv.auditcompliance;

import com.gama.whiv.auditcompliance.entity.AuditLog;
import com.gama.whiv.auditcompliance.service.AuditComplianceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit-compliance")
@Slf4j
public class AuditComplianceController {
    private final AuditComplianceService auditComplianceService;

    @Autowired
    public AuditComplianceController(AuditComplianceService auditComplianceService) {
        this.auditComplianceService = auditComplianceService;
    }

    @GetMapping("/logs")
    public ResponseEntity<List<AuditLog>> getAuditLogs(
            @RequestParam(required = false) String entityType,
            @RequestParam(required = false) String entityId) {
        return ResponseEntity.ok(auditComplianceService.getAuditLogs(entityType, entityId));
    }

    @PostMapping("/add-log")
    public ResponseEntity<AuditLog> addAuditLog(@RequestBody AddAuditLogRequest request) {
        return ResponseEntity.ok(auditComplianceService.addAuditLog(request));
    }

    public record AddAuditLogRequest(
            String entityId,
            String entityType,
            String actionType,
            String performedBy,
            String description,
            String createdAt
    ) {}

    public record ComplianceReportRequest(
            String startDate,
            String endDate,
            String reportType
    ) {}
}