## **Controller: RolePermissionManagementController**

### **Purpose**
The `RolePermissionManagementController` handles the management of roles and their associated permissions. It provides endpoints to assign permissions to roles, remove permissions from roles, and fetch the permissions of a role.

---

### **Functions**

#### 1. **Assign Permissions to Role**
- **Endpoint**: `POST /api/roles/{roleId}/permissions`
- **Purpose**: Assign one or more permissions to a specific role.
- **Scenario**:
    - Used when a new feature is added and existing roles need updated permissions.
    - Example: Assigning `ViewTransactionReports` to the "Auditor" role.
- **Request DTO**:
  ```java
  public class AssignPermissionsRequest {
      private List<Long> permissionIds; // List of permission IDs to assign
  }
  ```
- **Response DTO**:
  ```java
  public class AssignPermissionsResponse {
      private Long roleId; // Role ID
      private String roleName; // Name of the role
      private List<String> permissions; // List of permission names now assigned to the role
      private String status; // e.g., "Permissions Assigned"
  }
  ```
- **Implementation**:
    - Validate that the role exists.
    - Ensure all permission IDs in the request exist.
    - Assign the permissions to the role and save the updates.

---

#### 2. **Remove Permissions from Role**
- **Endpoint**: `DELETE /api/roles/{roleId}/permissions`
- **Purpose**: Remove one or more permissions from a specific role.
- **Scenario**:
    - Used when permissions need to be restricted due to policy changes or role redefinition.
    - Example: Removing `ManageUsers` from the "Auditor" role.
- **Request DTO**:
  ```java
  public class RemovePermissionsRequest {
      private List<Long> permissionIds; // List of permission IDs to remove
  }
  ```
- **Response DTO**:
  ```java
  public class RemovePermissionsResponse {
      private Long roleId; // Role ID
      private String roleName; // Name of the role
      private List<String> remainingPermissions; // List of remaining permissions for the role
      private String status; // e.g., "Permissions Removed"
  }
  ```
- **Implementation**:
    - Validate that the role exists.
    - Check that the role currently has the permissions to be removed.
    - Remove the permissions and save the changes.

---

#### 3. **Get Permissions of a Role**
- **Endpoint**: `GET /api/roles/{roleId}/permissions`
- **Purpose**: Retrieve all permissions currently assigned to a specific role.
- **Scenario**:
    - Used during audits or role reviews to verify access rights.
    - Example: Fetching permissions for the "Admin" role during a compliance check.
- **Response DTO**:
  ```java
  public class GetRolePermissionsResponse {
      private Long roleId; // Role ID
      private String roleName; // Name of the role
      private List<String> permissions; // List of permission names assigned to the role
  }
  ```
- **Implementation**:
    - Validate that the role exists.
    - Query the database to fetch all permissions associated with the role.

---

#### 4. **Get All Roles and Their Permissions**
- **Endpoint**: `GET /api/roles/permissions`
- **Purpose**: Retrieve all roles in the system and their associated permissions.
- **Scenario**:
    - Used during administrative tasks to review the overall role-permission structure.
    - Example: Displaying roles and permissions in an admin dashboard.
- **Response DTO**:
  ```java
  public class GetAllRolesPermissionsResponse {
      private List<RolePermissions> roles; // List of roles with their permissions
  }

  public class RolePermissions {
      private Long roleId; // Role ID
      private String roleName; // Name of the role
      private List<String> permissions; // List of permission names assigned to the role
  }
  ```
- **Implementation**:
    - Fetch all roles and their associated permissions from the database.
    - Return a comprehensive list for display or audit purposes.

---

### **Summary of Use Cases**
- **Assign Permissions to Role**: Update roles with additional access rights as needed.
- **Remove Permissions from Role**: Restrict or redefine role access by removing permissions.
- **Get Permissions of a Role**: Audit or verify permissions assigned to a specific role.
- **Get All Roles and Their Permissions**: Review the complete role-permission structure for administrative oversight.

---
