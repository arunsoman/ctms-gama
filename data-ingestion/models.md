

---

# Data Models for Data Ingestion

## Overview
This document describes the data models for various types of data that the AML system allows Financial Institutions (FIs) to push. These models are the standardized schema used to validate, process, and store incoming data in the **Customer Information Repository**, **Transaction Repository**, and other subsystems.

---

## 1. **Customer Information (KYC Data)**

### Model: `Customer`
| **Field**           | **Type**     | **Description**                                            | **Required** |
|----------------------|--------------|------------------------------------------------------------|--------------|
| `customerId`         | `string`     | Unique identifier for the customer (National ID or FI ID). | Yes          |
| `firstName`          | `string`     | Customer's first name.                                     | Yes          |
| `middleName`         | `string`     | Customer's middle name (if applicable).                   | No           |
| `lastName`           | `string`     | Customer's last name.                                      | Yes          |
| `dateOfBirth`        | `date`       | Date of birth for individuals.                            | Yes          |
| `gender`             | `string`     | Gender of the individual (e.g., `Male`, `Female`, `Other`).| No           |
| `address`            | `string`     | Residential or business address.                          | Yes          |
| `email`              | `string`     | Customer's email address.                                  | No           |
| `phoneNumbers`       | `list`       | List of contact numbers (mobile and/or landline).          | Yes          |
| `documentType`       | `string`     | Type of identification document (e.g., `Passport`).        | Yes          |
| `documentId`         | `string`     | ID number of the identification document.                 | Yes          |
| `documentExpiryDate` | `date`       | Expiry date of the identification document.               | No           |
| `accountType`        | `string`     | Type of account (e.g., `Savings`, `Corporate`).           | Yes          |
| `riskRating`         | `string`     | Customer's risk rating as assessed by the FI.             | No           |

---

## 2. **Transaction Data**

### Model: `Transaction`
| **Field**             | **Type**     | **Description**                                            | **Required** |
|------------------------|--------------|------------------------------------------------------------|--------------|
| `transactionId`        | `string`     | Unique identifier for the transaction.                    | Yes          |
| `timestamp`            | `datetime`   | ISO 8601 timestamp of the transaction.                    | Yes          |
| `transactionType`      | `string`     | Type of transaction (e.g., `Credit`, `Debit`).            | Yes          |
| `amount`               | `decimal`    | Monetary value of the transaction.                        | Yes          |
| `currency`             | `string`     | ISO 4217 currency code (e.g., `USD`, `EUR`).              | Yes          |
| `purpose`              | `string`     | Description or purpose of the transaction.                | No           |
| `sourceAccountId`      | `string`     | Source account number.                                     | Yes          |
| `sourceAccountName`    | `string`     | Name of the source account holder.                        | No           |
| `destinationAccountId` | `string`     | Destination account number.                                | Yes          |
| `destinationAccountName`| `string`    | Name of the destination account holder.                   | No           |
| `latitude`             | `decimal`    | Latitude of the transaction location.                     | No           |
| `longitude`            | `decimal`    | Longitude of the transaction location.                    | No           |
| `channel`              | `string`     | Transaction channel (e.g., `ATM`, `Mobile App`).          | Yes          |
| `deviceId`             | `string`     | Device identifier for the transaction (e.g., IMEI).       | No           |
| `ipAddress`            | `string`     | IP address of the device initiating the transaction.      | No           |

---

## 3. **Account Activity Data**

### Model: `AccountActivity`
| **Field**            | **Type**     | **Description**                                            | **Required** |
|-----------------------|--------------|------------------------------------------------------------|--------------|
| `accountId`           | `string`     | Unique identifier for the account.                        | Yes          |
| `status`              | `string`     | Current status of the account (e.g., `Active`, `Dormant`).| Yes          |
| `flags`               | `list`       | List of flags associated with the account (e.g., `High Risk`).| No       |
| `totalDeposits`       | `decimal`    | Total deposit amount over a specified period.             | Yes          |
| `totalWithdrawals`    | `decimal`    | Total withdrawal amount over a specified period.          | Yes          |
| `transactionCount`    | `integer`    | Total number of transactions over a specified period.     | Yes          |
| `jointAccountHolders` | `list`       | Details of joint account holders (if applicable).         | No           |
| `beneficialOwners`    | `list`       | List of beneficial owners for corporate accounts.         | No           |

---

## 4. **Risk and Alert Data**

### Model: `RiskAlert`
| **Field**             | **Type**     | **Description**                                            | **Required** |
|------------------------|--------------|------------------------------------------------------------|--------------|
| `alertId`             | `string`     | Unique identifier for the alert.                          | Yes          |
| `timestamp`           | `datetime`   | Time when the alert was generated.                        | Yes          |
| `description`         | `string`     | Description of the suspicious activity.                   | Yes          |
| `type`                | `string`     | Type of alert (e.g., `High Value`, `Repeated Small Transactions`).| Yes |
| `riskScore`           | `decimal`    | Risk score associated with the alert.                     | No           |
| `actionTaken`         | `string`     | Action taken by the FI in response to the alert.          | No           |
| `followUpNotes`       | `string`     | Notes regarding follow-up actions or decisions.           | No           |

---

## 5. **Supplementary Data**

### Model: `SupplementaryData`
| **Field**             | **Type**     | **Description**                                            | **Required** |
|------------------------|--------------|------------------------------------------------------------|--------------|
| `sanctionMatch`       | `boolean`    | Indicates if the customer or transaction matches a sanctions list.| Yes  |
| `matchDetails`        | `string`     | Details about the matched sanctions entity.               | No           |
| `spendingPatterns`    | `list`       | Categorized breakdown of spending patterns.               | No           |
| `externalIdentifiers` | `list`       | Links to external databases (e.g., PEP listings).         | No           |

---

## Notes for Developers
- **Validation**:
  - Ensure all required fields are present and adhere to their respective formats.
  - Implement server-side validation for batch uploads and API submissions.
- **Extensibility**:
  - New fields can be added by extending the base models.
- **Compliance**:
  - Ensure data models align with AML regulations and privacy laws.

---
