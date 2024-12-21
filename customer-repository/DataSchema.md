Documentation of the **data schema** for the **Customer Information Repository (CIR)**, detailing where each dataset is stored across **PostgreSQL**, **Neo4j**, and **Elasticsearch**.

---

### **1. KYC Data**
Stores customer identity and verification details.

#### **Schema (PostgreSQL)**
```sql
CREATE TABLE kyc (
    customer_id BIGINT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10),
    nationality VARCHAR(100),
    government_id_type VARCHAR(50),
    government_id_number VARCHAR(100) UNIQUE,
    tax_id_number VARCHAR(100),
    biometric_data TEXT, -- Encrypted biometric information
    primary_phone VARCHAR(15),
    secondary_phone VARCHAR(15),
    email VARCHAR(100),
    residential_address TEXT,
    business_address TEXT,
    occupation VARCHAR(100),
    employer VARCHAR(100),
    industry VARCHAR(100),
    annual_income NUMERIC(15, 2),
    source_of_funds VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

#### **Storage**:
- **PostgreSQL**: Primary storage for KYC data.
- **Elasticsearch**: Indexed for search and analytics (e.g., full-text search for customer names, addresses).

---

### **2. Transaction Data**
Stores financial transaction details.

#### **Schema (PostgreSQL)**
```sql
CREATE TABLE transactions (
    transaction_id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES kyc(customer_id),
    transaction_date_time TIMESTAMP NOT NULL,
    transaction_type VARCHAR(50) NOT NULL, -- Credit, Debit, Transfer, etc.
    transaction_amount NUMERIC(15, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    sender_details TEXT,
    receiver_details TEXT,
    geolocation VARCHAR(255),
    channel VARCHAR(50), -- Mobile app, ATM, branch, online
    device_info TEXT, -- IMEI or browser fingerprint
    flagged_status VARCHAR(50), -- Suspicious, High-Risk, etc.
    risk_score NUMERIC(5, 2),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

#### **Storage**:
- **PostgreSQL**: Stores detailed transaction records.
- **Elasticsearch**: Indexed for quick lookups and aggregations (e.g., flagged transactions, high-value transactions).
- **Neo4j**: Transaction relationships (e.g., customer-to-counterparty) are stored in graph form.

---

### **3. Account Data**
Manages details of customer accounts.

#### **Schema (PostgreSQL)**
```sql
CREATE TABLE accounts (
    account_id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES kyc(customer_id),
    account_number VARCHAR(50) UNIQUE NOT NULL,
    account_type VARCHAR(50), -- Savings, Checking, etc.
    account_status VARCHAR(20), -- Active, Dormant, Closed
    creation_date DATE NOT NULL,
    closure_date DATE,
    transaction_limit NUMERIC(15, 2),
    linked_devices TEXT, -- JSON array of linked device identifiers
    overdraft_limit NUMERIC(15, 2),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

#### **Storage**:
- **PostgreSQL**: Primary storage for account details.
- **Elasticsearch**: Indexed for searches like account status or transaction limits.

---

### **4. Risk Assessment Data**
Tracks risk scores and matches to sanctions or PEPs.

#### **Schema (PostgreSQL)**
```sql
CREATE TABLE risk_assessments (
    customer_id BIGINT PRIMARY KEY REFERENCES kyc(customer_id),
    overall_risk_score NUMERIC(5, 2),
    geographic_risk_score NUMERIC(5, 2),
    transactional_risk_score NUMERIC(5, 2),
    relationship_risk_score NUMERIC(5, 2),
    sanctions_match BOOLEAN DEFAULT FALSE,
    pep_match BOOLEAN DEFAULT FALSE,
    adverse_media_details TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

#### **Storage**:
- **PostgreSQL**: Stores detailed risk scores and sanctions/PEP matches.
- **Elasticsearch**: Indexed for real-time querying and dashboard visualizations.

---

### **5. Behavioral Data**
Tracks customer behavior, including patterns and anomalies.

#### **Schema (PostgreSQL)**
```sql
CREATE TABLE behavioral_data (
    customer_id BIGINT PRIMARY KEY REFERENCES kyc(customer_id),
    average_transaction_frequency NUMERIC(10, 2),
    average_transaction_value NUMERIC(15, 2),
    preferred_channels TEXT, -- JSON array of preferred transaction channels
    usual_locations TEXT, -- JSON array of geolocations
    typical_transaction_hours TEXT, -- JSON array of time ranges
    unusual_patterns TEXT, -- Description of detected anomalies
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

#### **Storage**:
- **PostgreSQL**: Stores structured behavioral data.
- **Neo4j**: Used for detecting anomalies in network behaviors.
- **Elasticsearch**: Indexed for analytics and anomaly detection.

---

### **6. Relationship Data**
Manages customer-to-entity relationships.

#### **Schema (Neo4j)**
```cypher
CREATE (c:Customer {customerId: '12345', name: 'John Doe'})
CREATE (p:Person {name: 'Jane Doe', riskLevel: 'High'})
CREATE (c)-[:TRANSACTS_WITH {transactionAmount: 1000, date: '2024-01-01'}]->(p)
```

#### **Storage**:
- **Neo4j**: Primary storage for graph relationships.
- **Elasticsearch**: Indexed for graph data aggregation and analytics.

---

### **7. Alerts and Case Data**
Tracks suspicious activity alerts and case investigations.

#### **Schema (PostgreSQL)**
```sql
CREATE TABLE alerts (
    alert_id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES kyc(customer_id),
    alert_type VARCHAR(50), -- KYC Mismatch, Suspicious Transaction, etc.
    alert_status VARCHAR(20), -- Open, Resolved, Escalated
    alert_priority NUMERIC(5, 2),
    alert_details TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE cases (
    case_id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES kyc(customer_id),
    case_type VARCHAR(50), -- Investigation, Compliance
    case_status VARCHAR(20), -- Open, Closed, Escalated
    evidence_details TEXT,
    investigator_comments TEXT,
    resolution_details TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

#### **Storage**:
- **PostgreSQL**: Stores alerts and cases for compliance and investigations.
- **Elasticsearch**: Indexed for tracking high-priority alerts and unresolved cases.

---

### **8. External Data**
Stores matches to sanctions lists, PEPs, and adverse media.

#### **Schema (PostgreSQL)**
```sql
CREATE TABLE external_data (
    customer_id BIGINT PRIMARY KEY REFERENCES kyc(customer_id),
    sanctions_list_match BOOLEAN DEFAULT FALSE,
    watchlist_match BOOLEAN DEFAULT FALSE,
    adverse_media_links TEXT, -- JSON array of media links
    third_party_risk_details TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

#### **Storage**:
- **PostgreSQL**: Primary storage for external risk data.
- **Elasticsearch**: Indexed for sanctions and watchlist checks.

---

### **Data Storage Summary**

| **Data Type**         | **Primary Storage** | **Indexed in Elasticsearch?** | **Neo4j Usage?**       |
|-----------------------|--------------------|--------------------------------|-----------------------|
| KYC Data              | PostgreSQL         | Yes                            | No                    |
| Transaction Data      | PostgreSQL         | Yes                            | Yes (relationships)   |
| Account Data          | PostgreSQL         | Yes                            | No                    |
| Risk Assessment Data  | PostgreSQL         | Yes                            | No                    |
| Behavioral Data       | PostgreSQL         | Yes                            | Yes (anomaly detection) |
| Relationship Data     | Neo4j              | Yes                            | Yes                   |
| Alerts and Cases      | PostgreSQL         | Yes                            | No                    |
| External Data         | PostgreSQL         | Yes                            | No                    |

---

This schema ensures **optimized storage** and **efficient querying** for the CIR, leveraging the strengths of PostgreSQL, Neo4j, and Elasticsearch.