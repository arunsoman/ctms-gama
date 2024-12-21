# Models Documentation

This document provides a detailed description of the models used in the FI Onboarding Module. Each model includes its purpose, attributes, relationships, and other important details.

## 1. `FiUser`
- **Purpose**: Represents the users associated with a financial institution, responsible for managing access and operations within the system.
- **Attributes**:
    - `id` (Long): Unique identifier for the user.
    - `username` (String): Login credential for the user, must be unique.
    - `password` (String): Encrypted password for authentication.
    - `email` (String): Email address for user communications.
    - `status` (String): Status of the user account (e.g., Active, Suspended, Locked).
    - `createdAt` (Timestamp): Timestamp of when the user was created.
    - `updatedAt` (Timestamp): Timestamp of the last update to the user details.
- **Relationships**:
    - `roles` (Set<FiRole>): Many-to-Many relationship with `FiRole` to assign multiple roles to a user.

## 2. `FiRole`
- **Purpose**: Defines roles that can be assigned to users, determining their access and permissions within the system.
- **Attributes**:
    - `id` (Long): Unique identifier for the role.
    - `roleName` (String): Name of the role (e.g., Administrator, Auditor).
    - `description` (String): Brief description of the role's responsibilities.
    - `createdAt` (Timestamp): Timestamp of when the role was created.
    - `updatedAt` (Timestamp): Timestamp of the last update to the role.
- **Relationships**:
    - `permissions` (Set<FiPermission>): Many-to-Many relationship with `FiPermission` to define permissions for the role.
    - `users` (Set<FiUser>): Many-to-Many relationship with `FiUser` for user-role assignments.

## 3. `FiPermission`
- **Purpose**: Represents granular permissions within the system, defining specific actions that can be performed.
- **Attributes**:
    - `id` (Long): Unique identifier for the permission.
    - `permissionName` (String): Name of the permission (e.g., View Reports, Manage Users).
    - `description` (String): Brief description of what the permission allows.
    - `createdAt` (Timestamp): Timestamp of when the permission was created.
    - `updatedAt` (Timestamp): Timestamp of the last update to the permission.
- **Relationships**:
    - `roles` (Set<FiRole>): Many-to-Many relationship with `FiRole` to assign permissions to roles.

## 4. `ApiKey`
- **Purpose**: Manages API keys for financial institutions, enabling secure and controlled access to the system's APIs.
- **Attributes**:
    - `id` (Long): Unique identifier for the API key.
    - `key` (String): The actual API key used for authentication.
    - `fiId` (Long): Identifier of the associated financial institution.
    - `status` (String): Status of the API key (e.g., Active, Revoked).
    - `createdAt` (Timestamp): Timestamp of when the API key was generated.
    - `expiresAt` (Timestamp): Expiration timestamp for the API key.
    - `usageCount` (Integer): Number of times the key has been used.

## 5. `AuditTrail`
- **Purpose**: Tracks all significant actions and events within the system for compliance and debugging purposes.
- **Attributes**:
    - `id` (Long): Unique identifier for the audit trail entry.
    - `entityName` (String): Name of the entity affected (e.g., FiUser, ApiKey).
    - `entityId` (Long): Identifier of the affected entity.
    - `action` (String): Description of the action performed (e.g., Created, Updated, Deleted).
    - `performedBy` (String): Username of the user who performed the action.
    - `performedAt` (Timestamp): Timestamp of when the action was performed.
    - `details` (String): Additional details about the action (e.g., changes made).

## 6. `FinancialInstitution`
- **Purpose**: Represents the onboarded financial institutions, storing core details about each institution.
- **Attributes**:
    - `id` (Long): Unique identifier for the financial institution.
    - `name` (String): Name of the financial institution.
    - `address` (String): Registered address of the institution.
    - `contactPerson` (String): Name of the contact person for the institution.
    - `email` (String): Email address for communication.
    - `phone` (String): Contact number for the institution.
    - `status` (String): Status of the institution (e.g., Active, Suspended).
    - `createdAt` (Timestamp): Timestamp of when the institution was onboarded.
    - `updatedAt` (Timestamp): Timestamp of the last update to the institution details.

## 7. `Notification`
- **Purpose**: Stores information about notifications sent to financial institutions and their users.
- **Attributes**:
    - `id` (Long): Unique identifier for the notification.
    - `recipient` (String): Identifier for the recipient (e.g., user ID or FI ID).
    - `message` (String): Content of the notification.
    - `type` (String): Type of notification (e.g., Email, SMS).
    - `status` (String): Status of the notification (e.g., Sent, Failed).
    - `sentAt` (Timestamp): Timestamp of when the notification was sent.
    - `createdAt` (Timestamp): Timestamp of when the notification was created.

## Relationships Summary
- `FiUser` ↔ `FiRole`: Many-to-Many relationship for assigning roles to users.
- `FiRole` ↔ `FiPermission`: Many-to-Many relationship for defining role permissions.
- `ApiKey` ↔ `FinancialInstitution`: One-to-Many relationship for managing API keys per institution.
- `AuditTrail` tracks changes and actions across all entities.

---

This document serves as a comprehensive reference for all models used in the FI Onboarding Module, outlining their purpose, structure, and relationships.
