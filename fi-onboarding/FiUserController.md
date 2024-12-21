Here’s a detailed breakdown for a **Spring Controller** including its DTOs, functions, scenarios, and purpose. We'll document **one controller at a time** for clarity.

---

## **Controller: FiUserController**

### **Purpose**
The `FiUserController` is responsible for managing FI users. It provides endpoints to create, update, deactivate, and fetch users, along with role assignment functionalities.

---

### **Functions**

#### 1. **Create FI User**
- **Endpoint**: `POST /api/fi/users`
- **Purpose**: Create a new user for a financial institution.
- **Scenario**:
    - Used when onboarding a new FI user (e.g., adding an FI administrator).
    - Ensures the user is assigned roles and initialized with the required credentials.
- **Request DTO**:
  ```java
  public class CreateFiUserRequest {
      private String username;
      private String email;
      private String password;
      private Long fiId; // Financial Institution ID
      private List<Long> roleIds; // List of Role IDs to assign
  }
  ```
- **Response DTO**:
  ```java
  public class CreateFiUserResponse {
      private Long id;
      private String username;
      private String email;
      private List<String> roles; // Role names assigned to the user
      private String status; // e.g., "Created"
  }
  ```
- **Implementation**:
    - Validate if the FI exists.
    - Check if the roles exist and can be assigned.
    - Hash the password and save the user.

---

#### 2. **Get All FI Users**
- **Endpoint**: `GET /api/fi/users`
- **Purpose**: Retrieve a list of all users associated with a financial institution.
- **Scenario**:
    - Used to fetch all users during audits or to display in admin dashboards.
    - Can filter by status or role.
- **Request Parameters**:
    - `fiId`: (Optional) Filter by FI.
    - `status`: (Optional) Filter by user status (e.g., Active, Suspended).
- **Response DTO**:
  ```java
  public class GetFiUsersResponse {
      private Long id;
      private String username;
      private String email;
      private String status; // e.g., Active, Suspended
      private List<String> roles;
  }
  ```
- **Implementation**:
    - Query the database with optional filters.
    - Return paginated results if necessary.

---

#### 3. **Update FI User**
- **Endpoint**: `PUT /api/fi/users/{id}`
- **Purpose**: Update details of an existing FI user.
- **Scenario**:
    - Used to change user email, status, or assigned roles.
    - Example: Resetting a user's account after they’re locked.
- **Request DTO**:
  ```java
  public class UpdateFiUserRequest {
      private String email;
      private String status; // e.g., Active, Locked
      private List<Long> roleIds; // Updated roles
  }
  ```
- **Response DTO**:
  ```java
  public class UpdateFiUserResponse {
      private Long id;
      private String username;
      private String email;
      private List<String> roles; // Updated role names
      private String status; // Updated status
  }
  ```
- **Implementation**:
    - Check if the user exists.
    - Validate new roles and apply updates.

---

#### 4. **Deactivate FI User**
- **Endpoint**: `DELETE /api/fi/users/{id}`
- **Purpose**: Soft delete an FI user by marking their status as inactive.
- **Scenario**:
    - Used to deactivate users who no longer need access (e.g., resigned employees).
- **Response DTO**:
  ```java
  public class DeactivateFiUserResponse {
      private Long id;
      private String status; // Updated status, e.g., "Deactivated"
  }
  ```
- **Implementation**:
    - Check if the user exists and is active.
    - Update their status to `Inactive`.

---

### **Summary of Use Cases**
- **Create FI User**: During onboarding or role expansion.
- **Get All FI Users**: For audit trails or displaying user lists.
- **Update FI User**: To adjust user details or roles.
- **Deactivate FI User**: To revoke user access securely.

---

Would you like to proceed with another controller, or is additional detail required for this one? Let me know!