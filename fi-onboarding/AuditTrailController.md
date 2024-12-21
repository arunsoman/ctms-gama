## **Controller: AuditTrailController**

### **Purpose**
The `AuditTrailController` is responsible for managing and retrieving audit logs for system events. It provides endpoints to log actions, fetch audit logs, and filter logs by various criteria to ensure accountability and traceability.

---

### **Functions**

#### 1. **Log Audit Event**
- **Endpoint**: `POST /api/audit/log`
- **Purpose**: Log an event into the audit trail for compliance and debugging purposes.
- **Scenario**:
    - Used whenever a significant action is performed in the system (e.g., user creation, role assignment, or API key revocation).
    - Example: Logging the creation of a new financial institution.
- **Request DTO**:
  ```java
  public class LogAuditEventRequest {
      private String entityName; // Name of the entity (e.g., FiUser, ApiKey)
      private Long entityId; // Identifier of the entity
      private String action; // Action performed (e.g., Created, Updated, Deleted)
      private String performedBy; // Username of the actor
      private String details; // Optional details about the action
  }
  ```
- **Response DTO**:
  ```java
  public class LogAuditEventResponse {
      private Long id; // Audit log ID
      private String status; // e.g., "Logged"
      private Timestamp loggedAt; // Timestamp of the logged event
  }
  ```
- **Implementation**:
    - Validate mandatory fields (e.g., `entityName`, `entityId`, `action`).
    - Save the event details to the audit trail database.

---

#### 2. **Get All Audit Logs**
- **Endpoint**: `GET /api/audit/logs`
- **Purpose**: Retrieve all audit logs for the system.
- **Scenario**:
    - Used by administrators to monitor system actions or during compliance audits.
    - Example: Reviewing all actions performed on the `FiUser` entity.
- **Request Parameters**:
    - `entityName`: (Optional) Filter logs by entity name.
    - `action`: (Optional) Filter logs by action type (e.g., Created, Updated, Deleted).
    - `performedBy`: (Optional) Filter logs by the actor’s username.
    - `dateRange`: (Optional) Filter logs by a date range.
- **Response DTO**:
  ```java
  public class GetAuditLogsResponse {
      private List<AuditLog> logs; // List of retrieved audit logs
  }

  public class AuditLog {
      private Long id; // Audit log ID
      private String entityName; // Name of the entity (e.g., FiUser, ApiKey)
      private Long entityId; // Identifier of the entity
      private String action; // Action performed
      private String performedBy; // Username of the actor
      private String details; // Additional details
      private Timestamp performedAt; // Timestamp of the action
  }
  ```
- **Implementation**:
    - Query the database with optional filters (entity, action, actor, or date range).
    - Provide paginated results for large datasets.

---

#### 3. **Get Audit Logs for an Entity**
- **Endpoint**: `GET /api/audit/logs/entity/{entityName}/{entityId}`
- **Purpose**: Retrieve all audit logs for a specific entity.
- **Scenario**:
    - Used to track the history of changes or actions performed on a specific entity.
    - Example: Fetching all audit logs for a specific user ID in `FiUser`.
- **Response DTO**:
  ```java
  public class GetEntityAuditLogsResponse {
      private List<AuditLog> logs; // List of retrieved logs for the entity
  }
  ```
- **Implementation**:
    - Validate the entity name and ID.
    - Fetch logs where `entityName` and `entityId` match the request parameters.

---

#### 4. **Get Audit Logs by User**
- **Endpoint**: `GET /api/audit/logs/user/{username}`
- **Purpose**: Retrieve all audit logs performed by a specific user.
- **Scenario**:
    - Used to monitor actions performed by a specific user (e.g., during investigations).
    - Example: Fetching logs for an administrator who assigned permissions to roles.
- **Response DTO**:
  ```java
  public class GetUserAuditLogsResponse {
      private List<AuditLog> logs; // List of retrieved logs for the user
  }
  ```
- **Implementation**:
    - Validate the username.
    - Fetch logs where `performedBy` matches the specified username.

---

### **Summary of Use Cases**
- **Log Audit Event**: Record significant actions for compliance and debugging purposes.
- **Get All Audit Logs**: Monitor overall system activity or perform compliance audits.
- **Get Audit Logs for an Entity**: Track the history of changes for a specific entity.
- **Get Audit Logs by User**: Monitor activity performed by a specific user.

---