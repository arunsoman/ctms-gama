package com.gama.whiv.auditcompliance;

import com.gama.whiv.auditcompliance.AuditLog;
import com.gama.whiv.auditcompliance.AuditLogRepository;
import com.gama.whiv.auditcompliance.AuditComplianceController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AuditComplianceService {
    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditComplianceService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public AuditLog addAuditLog(AuditComplianceController.AddAuditLogRequest request) {
        AuditLog log = new AuditLog();
        log.setEntityId(request.entityId());
        log.setEntityType(request.entityType());
        log.setActionType(request.actionType());
        log.setPerformedBy(request.performedBy());
        log.setDescription(request.description());
        log.setCreatedAt(LocalDateTime.parse(request.createdAt()));
        
        return auditLogRepository.save(log);
    }

    public List<AuditLog> getAuditLogs(String entityType, String entityId) {
        if (entityType != null && entityId != null) {
            return auditLogRepository.findByEntityTypeAndEntityId(entityType, entityId);
        }
        return auditLogRepository.findAll();
    }
}