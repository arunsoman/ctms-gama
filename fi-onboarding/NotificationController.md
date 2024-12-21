## **Controller: NotificationController**

### **Purpose**
The `NotificationController` manages notifications sent to financial institutions and their users. It provides endpoints to send notifications, fetch notification history, and update notification preferences.

---

### **Functions**

#### 1. **Send Notification**
- **Endpoint**: `POST /api/notifications/send`
- **Purpose**: Send a notification to a user or financial institution.
- **Scenario**:
    - Used to inform users about events such as API key expiration or onboarding milestones.
    - Example: Sending an email to an FI admin about a pending SLA renewal.
- **Request DTO**:
  ```java
  public class SendNotificationRequest {
      private String recipient; // Recipient ID (user ID or FI ID)
      private String message; // Content of the notification
      private String type; // Notification type (e.g., EMAIL, SMS)
  }
  ```
- **Response DTO**:
  ```java
  public class SendNotificationResponse {
      private Long id; // Notification ID
      private String status; // e.g., "Sent", "Failed"
      private Timestamp sentAt; // Timestamp when the notification was sent
  }
  ```
- **Implementation**:
    - Validate the recipient exists (user or FI).
    - Dispatch the notification via the appropriate channel (e.g., email, SMS).
    - Log the notification details in the database.

---

#### 2. **Get Notification History**
- **Endpoint**: `GET /api/notifications/history`
- **Purpose**: Retrieve the history of notifications sent to a specific recipient.
- **Scenario**:
    - Used by administrators to audit communication logs or by users to review past notifications.
    - Example: Fetching all notifications sent to a particular FI.
- **Request Parameters**:
    - `recipient`: (Optional) Filter notifications by recipient ID.
    - `type`: (Optional) Filter by notification type (e.g., EMAIL, SMS).
    - `dateRange`: (Optional) Filter by a date range.
- **Response DTO**:
  ```java
  public class GetNotificationHistoryResponse {
      private List<NotificationLog> notifications; // List of notification logs
  }

  public class NotificationLog {
      private Long id; // Notification ID
      private String recipient; // Recipient ID
      private String message; // Notification content
      private String type; // Notification type (e.g., EMAIL, SMS)
      private String status; // e.g., "Sent", "Failed"
      private Timestamp sentAt; // Timestamp when the notification was sent
  }
  ```
- **Implementation**:
    - Query the database with optional filters (recipient, type, or date range).
    - Provide paginated results for large datasets.

---

#### 3. **Update Notification Preferences**
- **Endpoint**: `PUT /api/notifications/preferences/{recipientId}`
- **Purpose**: Update notification preferences for a specific recipient.
- **Scenario**:
    - Used by users or administrators to enable or disable specific types of notifications.
    - Example: An FI admin opting out of SMS notifications for API usage alerts.
- **Request DTO**:
  ```java
  public class UpdateNotificationPreferencesRequest {
      private List<String> enabledTypes; // List of notification types to enable
      private List<String> disabledTypes; // List of notification types to disable
  }
  ```
- **Response DTO**:
  ```java
  public class UpdateNotificationPreferencesResponse {
      private String recipient; // Recipient ID
      private List<String> enabledTypes; // Updated list of enabled notification types
      private List<String> disabledTypes; // Updated list of disabled notification types
      private String status; // e.g., "Updated"
  }
  ```
- **Implementation**:
    - Validate the recipient ID.
    - Update the notification preferences in the database.

---

#### 4. **Resend Notification**
- **Endpoint**: `POST /api/notifications/resend/{id}`
- **Purpose**: Resend a previously failed notification.
- **Scenario**:
    - Used to retry sending notifications that failed due to delivery issues.
    - Example: Resending a failed email about an API key expiration.
- **Response DTO**:
  ```java
  public class ResendNotificationResponse {
      private Long id; // Notification ID
      private String status; // Updated status, e.g., "Resent", "Failed"
      private Timestamp resentAt; // Timestamp when the notification was resent
  }
  ```
- **Implementation**:
    - Check the notification's existing status (must be "Failed").
    - Retry sending the notification via the appropriate channel.

---

### **Summary of Use Cases**
- **Send Notification**: Inform users or institutions about events or actions.
- **Get Notification History**: Audit or review past notifications for a user or institution.
- **Update Notification Preferences**: Customize the types of notifications a user or institution receives.
- **Resend Notification**: Retry sending failed notifications to ensure delivery.

---
