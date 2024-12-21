# Data Ingestion Layer

## Overview
The Data Ingestion Layer is the entry point of the AML system. Its primary responsibility is to securely and efficiently receive data from Financial Institutions (FIs) in various formats. The module ensures that the incoming data is ingested without loss, validated for completeness, and handed over to downstream systems for further processing.

This module also leverages API keys issued by the **FI Onboarding Module** to authenticate and track the data submissions by each FI.

## Scope of Work
The Data Ingestion Layer must provide the following functionalities:

### 1. Secure Data Transfer
- **Objective**: Ensure data is securely transmitted between FIs and the AML system.
- **Features to Implement**:
  - Support for secure transfer protocols such as HTTPS, SFTP, and FTPS.
  - Implement robust encryption mechanisms for data in transit.
  - Integrate authentication and authorization (e.g., API keys, OAuth2) to restrict access.
  - Logging and monitoring of all incoming data transfers.

### 2. Multi-Format Data Support
- **Objective**: Accept and process data in various formats.
- **Features to Implement**:
  - Parsing support for JSON, XML, CSV, and other common data formats.
  - Extensible architecture to add support for new formats as needed.
  - Provide data format validation before further processing.

### 3. Real-Time and Batch Processing
- **Objective**: Support both real-time data streams and batch uploads.
- **Features to Implement**:
  - Build an API endpoint for real-time ingestion with rate limiting and throttling.
  - Enable batch file uploads via secure file transfer protocols.
  - Provide feedback on batch file processing status (e.g., success, failure, partial success).

### 4. Error Handling and Reporting
- **Objective**: Ensure robustness by identifying and handling errors in incoming data.
- **Features to Implement**:
  - Validate incoming data for required fields and correct data types.
  - Return detailed error messages for incomplete or malformed data submissions.
  - Log errors for debugging and provide real-time monitoring for ingestion issues.
  - Generate detailed error reports and notify relevant stakeholders.

### 5. Data Source Monitoring
- **Objective**: Track the status and health of data ingestion from each FI.
- **Features to Implement**:
  - Build a monitoring dashboard showing the status of data transfers for each FI.
  - Set up automated alerts for failed or delayed data submissions.
  - Provide usage analytics for the volume and frequency of data ingested from each FI.

### 6. Scalability and Performance
- **Objective**: Ensure the system can handle increasing data volumes efficiently.
- **Features to Implement**:
  - Design the system to support distributed data ingestion for scalability.
  - Optimize ingestion pipelines for high-throughput and low-latency processing.
  - Implement queuing mechanisms to manage peak loads gracefully.

## Integration with FI Onboarding Module

### How API Keys Are Used
1. **API Key Generation and Validation**:
   - The FI Onboarding Module generates unique API keys for each FI during onboarding.
   - These API keys are securely stored in a central database along with metadata (e.g., FI Name, permissions, status).

2. **Authentication in the Data Ingestion Layer**:
   - Each data submission request must include the API key in the `Authorization` header (e.g., `Authorization: Bearer <API_KEY>`).
   - The Data Ingestion Layer validates the API key by:
     - Checking its correctness.
     - Verifying the API key's active status and permissions.
     - Mapping the key to the associated FI for tracking purposes.

3. **Tracking Submissions**:
   - Every submission is logged with the following details:
     - FI ID (derived from the API key).
     - Timestamp of ingestion.
     - Type and size of data submitted.
     - Submission status (success/failure).

4. **Rate Limiting**:
   - Rate limits are enforced per FI using their API key to prevent abuse or overload.
   - Example: A maximum of 10,000 transactions per hour.

5. **Revocation and Monitoring**:
   - API keys can be revoked or rotated by the FI Onboarding Module.
   - The Data Ingestion Layer fetches updated API key statuses dynamically to ensure security.

### Developer Implementation
- **Middleware for Validation**:
  - Build middleware that extracts the API key from the header and validates it against the FI Onboarding Module.
  - Return appropriate HTTP responses for unauthorized (`401`) or forbidden (`403`) requests.

- **Error Logging**:
  - Log every failed authentication attempt with reasons for auditing and debugging.

- **Monitoring Dashboard**:
  - Display metrics such as:
    - API usage by FI.
    - Rate limit violations.
    - Status of submissions (e.g., success, failure).

## Technology Recommendations
- **Programming Language**: Java, Kotlin, or Python.
- **Frameworks**: Spring Boot (for APIs), Apache Camel (for batch processing).
- **Message Queue**: Apache Kafka or RabbitMQ for real-time streams.
- **Monitoring Tools**: Prometheus, Grafana for ingestion status tracking.
- **Storage**: HDFS or Amazon S3 for temporary storage of batch files.

## Deliverables
- A functional Data Ingestion Layer supporting all listed requirements.
- Secure integration with the FI Onboarding Module using API keys.
- Comprehensive API documentation.
- Monitoring dashboard for data ingestion tracking.
- Test cases covering edge cases, scalability, and robustness.
- Detailed README and architecture diagrams.

---
