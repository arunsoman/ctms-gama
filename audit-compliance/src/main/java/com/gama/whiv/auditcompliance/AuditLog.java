package com.gama.whiv.auditcompliance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String entityId;
    private String entityType;
    private String actionType;
    private String performedBy;
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}