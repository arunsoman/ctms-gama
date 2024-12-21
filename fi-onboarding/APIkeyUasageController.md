## **Controller: ApiKeyController**

### **Purpose**
The `ApiKeyController` is responsible for managing API keys that enable secure access to the system's APIs for financial institutions. It provides endpoints for generating, revoking, and monitoring API keys.

---

### **Functions**

#### 1. **Generate API Key**
- **Endpoint**: `POST /api/fi/apikeys`
- **Purpose**: Create a new API key for a financial institution.
- **Scenario**:
    - Used when onboarding a new financial institution or when an existing institution needs a new API key.
    - Example: Issuing an API key for an institution to access reporting endpoints.
- **Request DTO**:
  ```java
  public class GenerateApiKeyRequest {
      private Long fiId; // Financial Institution ID
      private List<String> ipWhitelist; // Optional list of allowed IPs
      private Timestamp expiryDate; // Optional expiration date for the key
  }
  ```
- **Response DTO**:
  ```java
  public class GenerateApiKeyResponse {
      private Long id; // API key ID
      private String apiKey; // The generated API key
      private Long fiId; // Associated Financial Institution ID
      private Timestamp createdAt; // Timestamp when the key was generated
      private Timestamp expiryDate; // Expiration date, if applicable
      private String status; // e.g., "Active"
  }
  ```
- **Implementation**:
    - Validate that the financial institution exists.
    - Generate a secure API key using cryptographic methods.
    - Save the key with its metadata, including expiration and IP whitelist.

---

#### 2. **Get All API Keys**
- **Endpoint**: `GET /api/fi/apikeys`
- **Purpose**: Retrieve all API keys associated with financial institutions.
- **Scenario**:
    - Used by administrators to audit active API keys or review usage.
    - Example: Listing all API keys to monitor expiration and activity.
- **Request Parameters**:
    - `fiId`: (Optional) Filter by Financial Institution.
    - `status`: (Optional) Filter by key status (e.g., Active, Revoked).
- **Response DTO**:
  ```java
  public class GetApiKeysResponse {
      private Long id; // API key ID
      private String apiKey; // Masked API key for security (e.g., first 5 characters only)
      private Long fiId; // Associated Financial Institution ID
      private Timestamp createdAt; // Creation timestamp
      private Timestamp expiryDate; // Expiration timestamp, if applicable
      private String status; // e.g., Active, Revoked
  }
  ```
- **Implementation**:
    - Query the database with optional filters for FI ID or status.
    - Mask the full API key in the response for security reasons.

---

#### 3. **Revoke API Key**
- **Endpoint**: `PUT /api/fi/apikeys/{id}/revoke`
- **Purpose**: Revoke an existing API key.
- **Scenario**:
    - Used to disable a compromised or expired API key.
    - Example: Revoking a key after detecting unauthorized access attempts.
- **Response DTO**:
  ```java
  public class RevokeApiKeyResponse {
      private Long id; // API key ID
      private String status; // Updated status, e.g., "Revoked"
      private Timestamp revokedAt; // Timestamp of revocation
  }
  ```
- **Implementation**:
    - Validate that the key exists and is currently active.
    - Update the status to `Revoked` and log the revocation time.

---

#### 4. **Monitor API Key Usage**
- **Endpoint**: `GET /api/fi/apikeys/{id}/usage`
- **Purpose**: Retrieve usage metrics for a specific API key.
- **Scenario**:
    - Used to track key usage for billing or security audits.
    - Example: Reviewing an API key’s request volume over the past month.
- **Response DTO**:
  ```java
  public class ApiKeyUsageResponse {
      private Long id; // API key ID
      private Long fiId; // Associated Financial Institution ID
      private List<ApiKeyUsage> usageMetrics; // List of usage statistics
  }

  public class ApiKeyUsage {
      private Timestamp timestamp; // Time of the request
      private String endpointAccessed; // API endpoint accessed
      private String ipAddress; // IP address of the request
  }
  ```
- **Implementation**:
    - Query logs to retrieve usage data for the specified API key.
    - Aggregate metrics by endpoint and timestamp for reporting.

---

### **Summary of Use Cases**
- **Generate API Key**: Provide secure access to the system for financial institutions.
- **Get All API Keys**: Review and audit API key usage across institutions.
- **Revoke API Key**: Disable compromised or expired keys.
- **Monitor API Key Usage**: Track access patterns for security or billing purposes.

---

