## **Controller: FinancialInstitutionController**

### **Purpose**
The `FinancialInstitutionController` is responsible for managing financial institutions (FIs) in the system. It provides endpoints to onboard, update, fetch, and deactivate FIs.

---

### **Functions**

#### 1. **Onboard Financial Institution**
- **Endpoint**: `POST /api/fi`
- **Purpose**: Onboard a new financial institution into the system.
- **Scenario**:
    - Used during the initial setup to register a new financial institution.
    - Example: Onboarding a new bank with required contact and operational details.
- **Request DTO**:
  ```java
  public class OnboardFinancialInstitutionRequest {
      private String name; // Name of the financial institution
      private String address; // Address of the institution
      private String contactPerson; // Primary contact person
      private String email; // Contact email
      private String phone; // Contact phone number
      private String services; // List of supported services (comma-separated)
  }
  ```
- **Response DTO**:
  ```java
  public class OnboardFinancialInstitutionResponse {
      private Long id; // Financial institution ID
      private String name; // Name of the institution
      private String status; // e.g., "Onboarded"
      private Timestamp onboardedAt; // Timestamp of onboarding
  }
  ```
- **Implementation**:
    - Validate mandatory fields (e.g., `name`, `contactPerson`).
    - Ensure the institution doesn’t already exist.
    - Save the institution details and set the status to `Active`.

---

#### 2. **Get All Financial Institutions**
- **Endpoint**: `GET /api/fi`
- **Purpose**: Retrieve a list of all onboarded financial institutions.
- **Scenario**:
    - Used by administrators to review all registered institutions.
    - Example: Displaying all institutions on a dashboard for management.
- **Request Parameters**:
    - `status`: (Optional) Filter by institution status (e.g., Active, Suspended).
- **Response DTO**:
  ```java
  public class GetFinancialInstitutionsResponse {
      private List<FinancialInstitutionSummary> institutions; // List of financial institutions
  }

  public class FinancialInstitutionSummary {
      private Long id; // Institution ID
      private String name; // Name of the institution
      private String status; // Status of the institution (e.g., Active, Suspended)
      private String contactPerson; // Primary contact person
      private String email; // Contact email
      private String phone; // Contact phone number
      private Timestamp onboardedAt; // Onboarding timestamp
  }
  ```
- **Implementation**:
    - Query the database with optional status filters.
    - Return paginated results for large datasets.

---

#### 3. **Update Financial Institution**
- **Endpoint**: `PUT /api/fi/{id}`
- **Purpose**: Update details of an existing financial institution.
- **Scenario**:
    - Used to modify FI details such as contact information or supported services.
    - Example: Updating the email address of a bank’s primary contact.
- **Request DTO**:
  ```java
  public class UpdateFinancialInstitutionRequest {
      private String address; // New address
      private String contactPerson; // Updated contact person
      private String email; // Updated email
      private String phone; // Updated phone number
      private String services; // Updated list of supported services
  }
  ```
- **Response DTO**:
  ```java
  public class UpdateFinancialInstitutionResponse {
      private Long id; // Institution ID
      private String name; // Name of the institution
      private String status; // Updated status (e.g., "Updated")
      private Timestamp updatedAt; // Timestamp of the update
  }
  ```
- **Implementation**:
    - Validate that the institution exists.
    - Apply updates to the specified fields.

---

#### 4. **Deactivate Financial Institution**
- **Endpoint**: `DELETE /api/fi/{id}`
- **Purpose**: Deactivate an FI by marking its status as inactive.
- **Scenario**:
    - Used to suspend an institution due to regulatory issues or inactivity.
    - Example: Deactivating a bank that failed to meet compliance requirements.
- **Response DTO**:
  ```java
  public class DeactivateFinancialInstitutionResponse {
      private Long id; // Institution ID
      private String status; // Updated status, e.g., "Deactivated"
      private Timestamp deactivatedAt; // Timestamp of deactivation
  }
  ```
- **Implementation**:
    - Validate the institution’s current status.
    - Mark it as `Inactive` without deleting its records for compliance purposes.

---

### **Summary of Use Cases**
- **Onboard Financial Institution**: Register a new financial institution during initial setup.
- **Get All Financial Institutions**: Audit or review onboarded institutions.
- **Update Financial Institution**: Modify details such as address or services.
- **Deactivate Financial Institution**: Suspend an institution while retaining its records.

---

