package com.gama.whiv.auditcompliance;

import com.gama.whiv.auditcompliance.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityTypeAndEntityId(String entityType, String entityId);
    List<AuditLog> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}