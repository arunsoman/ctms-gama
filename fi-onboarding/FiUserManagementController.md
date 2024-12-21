## **Controller: FiUserManagementController**

### **Purpose**
The `FiUserManagementController` provides detailed user management functionalities, focusing on actions related to specific financial institution (FI) users. It includes endpoints for user status management, password resets, role reassignment, and fetching detailed user activity logs.

---

### **Functions**

#### 1. **Activate User**
- **Endpoint**: `PUT /api/fi/users/{id}/activate`
- **Purpose**: Activate an inactive user.
- **Scenario**:
    - Used when a deactivated user account needs to be re-enabled.
    - Example: Reinstating an FI administrator who had been previously suspended.
- **Response DTO**:
  ```java
  public class ActivateUserResponse {
      private Long id; // User ID
      private String username; // User's username
      private String status; // Updated status, e.g., "Active"
      private Timestamp activatedAt; // Timestamp of activation
  }
  ```
- **Implementation**:
    - Validate the user exists and is currently inactive.
    - Update the user status to `Active` and log the event.

---

#### 2. **Suspend User**
- **Endpoint**: `PUT /api/fi/users/{id}/suspend`
- **Purpose**: Suspend an active user.
- **Scenario**:
    - Used when a user violates policy or no longer requires immediate access.
    - Example: Temporarily suspending an FI operator for a compliance investigation.
- **Response DTO**:
  ```java
  public class SuspendUserResponse {
      private Long id; // User ID
      private String username; // User's username
      private String status; // Updated status, e.g., "Suspended"
      private Timestamp suspendedAt; // Timestamp of suspension
  }
  ```
- **Implementation**:
    - Validate the user exists and is currently active.
    - Update the user status to `Suspended` and log the event.

---

#### 3. **Reset User Password**
- **Endpoint**: `POST /api/fi/users/{id}/password-reset`
- **Purpose**: Reset a user’s password.
- **Scenario**:
    - Used when a user forgets their password or as part of security practices.
    - Example: Resetting an FI auditor's password after a suspicious login attempt.
- **Request DTO**:
  ```java
  public class PasswordResetRequest {
      private String newPassword; // New password for the user
  }
  ```
- **Response DTO**:
  ```java
  public class PasswordResetResponse {
      private Long id; // User ID
      private String username; // User's username
      private String status; // Status, e.g., "Password Reset Successfully"
      private Timestamp resetAt; // Timestamp of password reset
  }
  ```
- **Implementation**:
    - Validate the user exists.
    - Encrypt the new password and update it in the database.
    - Notify the user about the password change.

---

#### 4. **Reassign Roles**
- **Endpoint**: `PUT /api/fi/users/{id}/roles`
- **Purpose**: Reassign roles for a specific user.
- **Scenario**:
    - Used when a user's job responsibilities change, requiring updated roles.
    - Example: Promoting an FI operator to an administrator.
- **Request DTO**:
  ```java
  public class ReassignRolesRequest {
      private List<Long> roleIds; // List of new role IDs to assign
  }
  ```
- **Response DTO**:
  ```java
  public class ReassignRolesResponse {
      private Long id; // User ID
      private String username; // User's username
      private List<String> roles; // Updated roles for the user
      private String status; // e.g., "Roles Reassigned"
      private Timestamp updatedAt; // Timestamp of the role reassignment
  }
  ```
- **Implementation**:
    - Validate the user exists.
    - Check the roles exist and are valid for reassignment.
    - Update the user’s role associations and log the change.

---

#### 5. **Fetch User Activity Logs**
- **Endpoint**: `GET /api/fi/users/{id}/activity-logs`
- **Purpose**: Retrieve the activity logs for a specific user.
- **Scenario**:
    - Used to monitor user behavior or during an investigation.
    - Example: Fetching activity logs of an FI admin who modified roles and permissions.
- **Response DTO**:
  ```java
  public class UserActivityLogsResponse {
      private Long userId; // User ID
      private String username; // User's username
      private List<ActivityLog> logs; // List of activity logs
  }

  public class ActivityLog {
      private String action; // Action performed (e.g., "Updated Role", "Suspended User")
      private String entityName; // Entity affected (e.g., FiRole, FiUser)
      private Long entityId; // ID of the affected entity
      private Timestamp performedAt; // Timestamp of the action
  }
  ```
- **Implementation**:
    - Validate the user exists.
    - Query the audit logs for actions performed by the user.

---

### **Summary of Use Cases**
- **Activate User**: Enable access for an inactive user.
- **Suspend User**: Temporarily disable user access due to compliance or security issues.
- **Reset User Password**: Handle password reset requests securely.
- **Reassign Roles**: Update user responsibilities by modifying assigned roles.
- **Fetch User Activity Logs**: Audit or monitor specific user actions.

---

