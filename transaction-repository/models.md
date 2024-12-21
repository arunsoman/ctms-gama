

---

# Data Models for Customer Information and Transaction Repositories

## Overview
The **Customer Information Repository** and **Transaction Repository** operate on distinct yet interconnected data models. These models ensure seamless integration, scalability, and optimized storage for customer and transaction-related data.

---

## **1. Customer Information Repository Models**

### Model: `Customer`
| **Field**           | **Type**     | **Description**                                            | **Required** |
|----------------------|--------------|------------------------------------------------------------|--------------|
| `customerId`         | `string`     | Unique identifier for the customer.                       | Yes          |
| `fullName`           | `string`     | Customer’s full name.                                      | Yes          |
| `dateOfBirth`        | `date`       | Date of birth for individuals.                            | Yes          |
| `email`              | `string`     | Email address of the customer.                            | No           |
| `phoneNumbers`       | `list`       | List of customer’s contact numbers.                       | Yes          |
| `address`            | `string`     | Residential or business address.                          | Yes          |
| `accountIds`         | `list`       | List of associated account IDs.                           | Yes          |
| `riskScore`          | `decimal`    | Composite risk score for the customer.                    | Yes          |
| `riskCategory`       | `string`     | Risk classification (e.g., Low, Medium, High).            | Yes          |
| `kycStatus`          | `string`     | KYC verification status (e.g., Verified, Pending).         | Yes          |
| `beneficialOwners`   | `list`       | List of beneficial owners (for corporate accounts).       | No           |
| `linkedEntities`     | `list`       | List of related entities (e.g., joint account holders).   | No           |

---

### Model: `Account`
| **Field**           | **Type**     | **Description**                                            | **Required** |
|----------------------|--------------|------------------------------------------------------------|--------------|
| `accountId`          | `string`     | Unique identifier for the account.                        | Yes          |
| `customerId`         | `string`     | Identifier for the associated customer.                   | Yes          |
| `accountType`        | `string`     | Type of account (e.g., Savings, Corporate).               | Yes          |
| `balance`            | `decimal`    | Current account balance.                                   | Yes          |
| `currency`           | `string`     | ISO currency code (e.g., USD, EUR).                       | Yes          |
| `status`             | `string`     | Account status (e.g., Active, Dormant, Closed).           | Yes          |
| `creationDate`       | `datetime`   | Date and time the account was created.                    | Yes          |
| `linkedAccounts`     | `list`       | List of linked account IDs (e.g., joint accounts).         | No           |
| `accountFlags`       | `list`       | Flags associated with the account (e.g., High Risk).      | No           |

---

## **2. Transaction Repository Models**

### Model: `Transaction`
| **Field**           | **Type**     | **Description**                                            | **Required** |
|----------------------|--------------|------------------------------------------------------------|--------------|
| `transactionId`      | `string`     | Unique identifier for the transaction.                    | Yes          |
| `timestamp`          | `datetime`   | ISO 8601 timestamp of the transaction.                    | Yes          |
| `amount`             | `decimal`    | Monetary value of the transaction.                        | Yes          |
| `currency`           | `string`     | ISO 4217 currency code (e.g., USD, EUR).                  | Yes          |
| `transactionType`    | `string`     | Type of transaction (e.g., Credit, Debit, Transfer).      | Yes          |
| `sourceAccountId`    | `string`     | Identifier for the source account.                        | Yes          |
| `destinationAccountId` | `string`   | Identifier for the destination account.                   | Yes          |
| `description`        | `string`     | Additional details about the transaction.                 | No           |
| `channel`            | `string`     | Transaction channel (e.g., ATM, Online).                  | Yes          |
| `location`           | `object`     | Geolocation details (latitude, longitude, and country).   | No           |
| `deviceMetadata`     | `object`     | Metadata about the initiating device (e.g., IP address).  | No           |

---

### Model: `AggregatedTransactionMetrics`
| **Field**           | **Type**     | **Description**                                            | **Required** |
|----------------------|--------------|------------------------------------------------------------|--------------|
| `accountId`          | `string`     | Identifier for the associated account.                    | Yes          |
| `totalTransactions`  | `integer`    | Total number of transactions over a period.               | Yes          |
| `totalCredits`       | `decimal`    | Sum of credit transactions over a period.                 | Yes          |
| `totalDebits`        | `decimal`    | Sum of debit transactions over a period.                  | Yes          |
| `highestTransaction` | `decimal`    | Value of the highest transaction over a period.           | No           |
| `periodStart`        | `datetime`   | Start of the aggregation period.                          | Yes          |
| `periodEnd`          | `datetime`   | End of the aggregation period.                            | Yes          |

---

## **3. Relationships Between Models**

### **Customer and Account Relationship**
- Each customer (`customerId`) can have multiple accounts (`accountId`).
- Accounts are linked to a specific customer in the **Customer Information Repository**.
- Example:
  - Customer:
    ```json
    {
        "customerId": "C12345",
        "fullName": "John Doe",
        "accountIds": ["A001", "A002"]
    }
    ```
  - Accounts:
    ```json
    [
        {
            "accountId": "A001",
            "customerId": "C12345",
            "accountType": "Savings",
            "balance": 5000.00
        },
        {
            "accountId": "A002",
            "customerId": "C12345",
            "accountType": "Current",
            "balance": 2000.00
        }
    ]
    ```

---

### **Account and Transaction Relationship**
- Each transaction (`transactionId`) references a source and destination account (`sourceAccountId`, `destinationAccountId`).
- Accounts in the **Transaction Repository** are linked to customers in the **Customer Information Repository**.

- Example:
  - Transaction:
    ```json
    {
        "transactionId": "T001",
        "timestamp": "2024-12-01T12:00:00Z",
        "amount": 1000.00,
        "sourceAccountId": "A001",
        "destinationAccountId": "A003"
    }
    ```
  - Account Details:
    ```json
    {
        "accountId": "A001",
        "customerId": "C12345",
        "balance": 4000.00
    }
    ```

---

## Use Cases for Models

### **Customer Risk Assessment**
- Combine customer profile (`Customer`) with transaction patterns (`Transaction`) to calculate dynamic risk scores.

### **Fraud Detection**
- Use transactional relationships (`sourceAccountId`, `destinationAccountId`) to detect patterns like circular fund transfers.

### **Behavioral Insights**
- Query historical transactions (`Transaction`) linked to a customer (`Customer`) for behavioral analysis.

### **Regulatory Reporting**
- Aggregate transaction metrics (`AggregatedTransactionMetrics`) for high-value transaction reporting.

---

