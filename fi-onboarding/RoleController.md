## **Controller: FiRoleController**

### **Purpose**
The `FiRoleController` is responsible for managing roles within the system. It allows creating, updating, fetching, and deleting roles, along with assigning permissions to roles.

---

### **Functions**

#### 1. **Create Role**
- **Endpoint**: `POST /api/fi/roles`
- **Purpose**: Create a new role that can be assigned to users.
- **Scenario**:
    - Used when a financial institution needs a custom role for specific access control.
    - Example: Creating a "Compliance Auditor" role.
- **Request DTO**:
  ```java
  public class CreateRoleRequest {
      private String roleName; // Name of the role
      private String description; // Role description
      private List<Long> permissionIds; // List of permission IDs to assign
  }
  ```
- **Response DTO**:
  ```java
  public class CreateRoleResponse {
      private Long id; // Role ID
      private String roleName; // Name of the created role
      private List<String> permissions; // Permissions associated with the role
      private String status; // e.g., "Created"
  }
  ```
- **Implementation**:
    - Validate that the role name is unique.
    - Ensure all permissions exist before assigning.
    - Save the role with its permissions.

---

#### 2. **Get All Roles**
- **Endpoint**: `GET /api/fi/roles`
- **Purpose**: Retrieve all roles available in the system.
- **Scenario**:
    - Used by administrators to audit or display roles in management dashboards.
    - Example: Listing all roles during a permissions review.
- **Response DTO**:
  ```java
  public class GetRolesResponse {
      private Long id; // Role ID
      private String roleName; // Name of the role
      private String description; // Role description
      private List<String> permissions; // Permissions linked to the role
  }
  ```
- **Implementation**:
    - Fetch all roles and their associated permissions from the database.
    - Provide paginated results for large datasets.

---

#### 3. **Update Role**
- **Endpoint**: `PUT /api/fi/roles/{id}`
- **Purpose**: Update an existing role, including its permissions.
- **Scenario**:
    - Used to modify role details or update permissions (e.g., adding a new permission to an auditor role).
- **Request DTO**:
  ```java
  public class UpdateRoleRequest {
      private String roleName; // New role name (optional)
      private String description; // New description (optional)
      private List<Long> permissionIds; // Updated list of permissions
  }
  ```
- **Response DTO**:
  ```java
  public class UpdateRoleResponse {
      private Long id; // Role ID
      private String roleName; // Updated role name
      private List<String> permissions; // Updated permissions
      private String status; // e.g., "Updated"
  }
  ```
- **Implementation**:
    - Validate role existence and new permissions.
    - Apply updates and persist changes.

---

#### 4. **Delete Role**
- **Endpoint**: `DELETE /api/fi/roles/{id}`
- **Purpose**: Soft delete a role by marking it as inactive.
- **Scenario**:
    - Used to remove roles no longer needed while maintaining audit trails.
    - Example: Deactivating a deprecated "Guest Auditor" role.
- **Response DTO**:
  ```java
  public class DeleteRoleResponse {
      private Long id; // Role ID
      private String status; // Updated status, e.g., "Deleted"
  }
  ```
- **Implementation**:
    - Check if the role exists and is not assigned to active users.
    - Mark the role as inactive rather than hard deleting.

---

### **Summary of Use Cases**
- **Create Role**: Add new roles to address evolving access control needs.
- **Get All Roles**: Audit or manage roles for an institution.
- **Update Role**: Adjust role details or permissions as requirements change.
- **Delete Role**: Retire unused roles without losing historical records.

---

Would you like me to proceed with another controller, or refine details for this one? Let me know!