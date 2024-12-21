## **Controller: FiPermissionController**

### **Purpose**
The `FiPermissionController` manages permissions in the system. It provides endpoints to create, update, fetch, and delete permissions, ensuring granular control over access rights.

---

### **Functions**

#### 1. **Create Permission**
- **Endpoint**: `POST /api/fi/permissions`
- **Purpose**: Add a new permission that can be assigned to roles.
- **Scenario**:
    - Used when a new feature or module is added to the system requiring access control.
    - Example: Creating a permission like `ViewTransactionReports`.
- **Request DTO**:
  ```java
  public class CreatePermissionRequest {
      private String permissionName; // Name of the permission
      private String description; // Detailed description of what the permission allows
  }
  ```
- **Response DTO**:
  ```java
  public class CreatePermissionResponse {
      private Long id; // Permission ID
      private String permissionName; // Name of the created permission
      private String description; // Description of the permission
      private String status; // e.g., "Created"
  }
  ```
- **Implementation**:
    - Ensure the permission name is unique.
    - Save the permission with its details to the database.

---

#### 2. **Get All Permissions**
- **Endpoint**: `GET /api/fi/permissions`
- **Purpose**: Retrieve all available permissions in the system.
- **Scenario**:
    - Used during role creation or updates to display a list of permissions.
    - Example: Admin views all permissions to select those applicable to a new role.
- **Response DTO**:
  ```java
  public class GetPermissionsResponse {
      private Long id; // Permission ID
      private String permissionName; // Name of the permission
      private String description; // Description of what the permission allows
  }
  ```
- **Implementation**:
    - Query the database to fetch all permissions.
    - Return paginated results if the dataset is large.

---

#### 3. **Update Permission**
- **Endpoint**: `PUT /api/fi/permissions/{id}`
- **Purpose**: Modify details of an existing permission.
- **Scenario**:
    - Used to update permission descriptions for better clarity or change permission names to align with new standards.
- **Request DTO**:
  ```java
  public class UpdatePermissionRequest {
      private String permissionName; // New name for the permission (optional)
      private String description; // Updated description (optional)
  }
  ```
- **Response DTO**:
  ```java
  public class UpdatePermissionResponse {
      private Long id; // Permission ID
      private String permissionName; // Updated permission name
      private String description; // Updated description
      private String status; // e.g., "Updated"
  }
  ```
- **Implementation**:
    - Validate permission existence.
    - Apply updates and persist changes to the database.

---

#### 4. **Delete Permission**
- **Endpoint**: `DELETE /api/fi/permissions/{id}`
- **Purpose**: Soft delete a permission by marking it as inactive.
- **Scenario**:
    - Used to retire permissions no longer relevant (e.g., outdated module permissions).
    - Example: Deactivating `OldReportingAccess` after its module is deprecated.
- **Response DTO**:
  ```java
  public class DeletePermissionResponse {
      private Long id; // Permission ID
      private String status; // Updated status, e.g., "Deleted"
  }
  ```
- **Implementation**:
    - Ensure the permission is not actively assigned to any roles.
    - Mark it as inactive rather than hard deleting for audit purposes.

---

### **Summary of Use Cases**
- **Create Permission**: Add new permissions for emerging system features.
- **Get All Permissions**: Display permission lists during role management workflows.
- **Update Permission**: Modify existing permissions for better alignment with system changes.
- **Delete Permission**: Retire unused permissions to maintain a clean and manageable list.

---

Would you like to proceed with another controller, or refine this one further? Let me know! 