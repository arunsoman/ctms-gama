
---

## **1. Core Domain**
These subsystems represent the essential business logic that defines the AML system's value.

1. **Fraud Detection Engine**  
   - Detects suspicious activities and patterns in transactions, behavior, and networks.  

2. **Fraudulent Activity Module**  
   - Manages confirmed fraud cases, tracks actions, and provides feedback for refinement.  

3. **Alert Management Module**  
   - Central repository for ingesting, categorizing, and managing alerts.  

---

## **2. Supporting Subdomains**
Subsystems that provide specialized capabilities to support the core domain’s functionalities.

1. **AML Knowledge Repository**  
   - Central store for AML rules, typologies, and thresholds.  

2. **Behavioral Analytics Module**  
   - Identifies behavioral deviations and trends for customer profiling.  

3. **Temporal Analysis Module**  
   - Analyzes time-based patterns and anomalies in transactions.  

4. **Link Analysis Module**  
   - Maps and analyzes relationships between entities to detect high-risk connections.  

5. **Transaction Repository**  
   - Stores and manages high-volume transaction data for analytical processing.  

6. **Customer Information Repository**  
   - Centralized database for customer profiles, KYC data, and risk scores.  

7. **Suspicious Entity Detection Module**  
   - Identifies entities flagged as high-risk or involved in suspicious activities.  

8. **Transaction Pattern Analysis Module**  
   - Detects recurring transaction patterns that align with typologies like layering or structuring.  

9. **Geographical Risk Module**  
   - Evaluates transactions based on location-specific risks, such as sanctioned regions.  

10. **Device and Channel Analytics Module**  
   - Monitors the devices and channels used for transactions to identify anomalies.  

11. **Peer Group Analysis Module**  
   - Compares customer behavior to similar profiles to detect outliers.  

12. **High-Value Transaction Monitoring Module**  
   - Flags transactions exceeding predefined thresholds.  

13. **Anomaly Detection Module**  
   - Uses machine learning to uncover non-obvious anomalies in data.  

14. **Circular Transfer Detection Module**  
   - Identifies circular fund flows between multiple entities.  

15. **Time-Series Transaction Analysis Module**  
   - Analyzes trends in transactional data over specific time periods.  

16. **New Account Monitoring Module**  
   - Tracks high-risk activity in newly created accounts.  

17. **Dormant Account Activation Module**  
   - Monitors unusual activity in previously inactive accounts.  

18. **Multiple Account Aggregation Module**  
   - Consolidates transaction data across linked accounts for analysis.  

19. **Regulatory Compliance Module**  
   - Ensures system adherence to AML regulations, including SAR generation.  

---

## **3. Generic Subdomains**
Subsystems that provide reusable, technical, or infrastructural capabilities across the system.

1. **API Gateway Module**  
   - Manages API traffic, authentication, and routing.  

2. **Insights Dashboard Module**  
   - Provides visual analytics for transaction patterns, alerts, and case statuses.  

3. **Case Management Module**  
   - Tracks and manages the lifecycle of investigations into flagged activities.  

4. **Data Normalization and Validation Module**  
   - Prepares data for downstream processing by enforcing schema and removing duplicates.  

5. **Threat Intelligence Module**  
   - Integrates external feeds, such as sanctions lists and adverse media, to enhance risk evaluation.  

6. **Big Data Processing Module**  
   - Handles the ingestion, transformation, and storage of large volumes of data.  

7. **Real-Time Analytics Engine**  
   - Processes real-time transaction data to identify risks immediately.  

8. **Historical Data Analysis Module**  
   - Analyzes historical data for trends and retrospective anomaly detection.  

9. **ETL (Extract, Transform, Load) Module**  
   - Extracts data from sources, transforms it for analysis, and loads it into repositories.  

10. **Event Streaming Module**  
    - Enables real-time communication between components using streams like Kafka.  

11. **Data Archiving Module**  
    - Stores historical data for compliance and long-term analysis.  

12. **Logging and Monitoring Module**  
    - Tracks system performance and logs activities for debugging and compliance.  

13. **Identity and Access Management Module**  
    - Manages authentication, authorization, and user role assignments.  

14. **Load Balancer and Traffic Management Module**  
    - Distributes traffic efficiently across system components.  

15. **System Health Monitoring Module**  
    - Tracks the health of components and ensures uptime.  

---

### Context Map
Below is how these subsystems fit within the **Core Domain**, **Supporting Subdomains**, and **Generic Subdomains** in a high-level context map:

```plaintext
Core Domain:
  - Fraud Detection Engine
  - Fraudulent Activity Module
  - Alert Management Module

Supporting Subdomains:
  - AML Knowledge Repository
  - Behavioral Analytics Module
  - Temporal Analysis Module
  - Link Analysis Module
  - Customer Information Repository
  - Transaction Repository
  - Geographical Risk Module
  - Device and Channel Analytics Module
  - Suspicious Entity Detection Module
  - Peer Group Analysis Module
  - Anomaly Detection Module
  - Circular Transfer Detection Module
  - Regulatory Compliance Module

Generic Subdomains:
  - API Gateway Module
  - Insights Dashboard Module
  - Case Management Module
  - Data Normalization and Validation Module
  - Threat Intelligence Module
  - Big Data Processing Module
  - ETL Module
  - Real-Time Analytics Engine
  - Event Streaming Module
  - Logging and Monitoring Module
  - Identity and Access Management Module
```

---
