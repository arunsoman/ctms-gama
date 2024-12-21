# Gama WHIV - Anti-Money Laundering System

A comprehensive Anti-Money Laundering (AML) detection and compliance system built with Spring Boot microservices.

## Project Structure

The project consists of multiple microservices, each handling specific aspects of AML detection and compliance:

### Data Layer Modules
- Data Ingestion
- Data Normalization
- Customer Repository
- Transaction Repository

### Analytics Modules
- Fraud Detection
- Risk Scoring
- Pattern Recognition
- Entity Resolution
- Behavioral Analytics
- Link Analysis
- Geospatial Analysis
- Temporal Analysis

### Compliance and Reporting
- Regulatory Reporting
- Case Management
- Audit Compliance
- AML Knowledge

[... and more modules]

## Prerequisites

- Java 21
- Docker
- Docker Compose
- Gradle

## Building the Project

To build all modules:

```bash
./gradlew build
```

## Running with Docker

To start all services:

```bash
docker-compose up -d
```

## Service URLs

- Discovery Server: http://localhost:8761
- Data Ingestion: http://localhost:8081
- Data Normalization: http://localhost:8082
[... other services]

## Development

Each module follows a standard Spring Boot application structure and can be developed independently. 

## Contributing

Please follow the standard Git workflow:
1. Create a feature branch
2. Make your changes
3. Submit a pull request

## License

This project is proprietary and confidential.
