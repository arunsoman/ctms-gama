Here’s a **sample Java code** demonstrating how to interact with both **PostgreSQL** and **Neo4j**, using **Elasticsearch** as a shared layer for querying indexed data. The chosen scenario is:

### **Scenario: Unified Customer Search**
- Search for a customer by name.
- Retrieve:
  - KYC details and transaction history (from PostgreSQL).
  - Associated counterparties and suspicious networks (from Neo4j).
- Return a combined response to the user.

---

### **System Architecture**
1. **PostgreSQL**: Stores structured data like KYC and transactions.
2. **Neo4j**: Models graph relationships, such as customer-to-counterparty connections.
3. **Elasticsearch**: Acts as a unified search layer, storing indexed data from both PostgreSQL and Neo4j.

---

### **Sample Code**

#### **1. UnifiedSearchService.java**
This service interacts with PostgreSQL, Neo4j, and Elasticsearch to execute the search.

```java
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnifiedSearchService {

    @Autowired
    private PostgreSQLRepository postgreSQLRepository; // Interacts with PostgreSQL
    @Autowired
    private Neo4jRepository neo4jRepository; // Interacts with Neo4j
    @Autowired
    private RestHighLevelClient elasticsearchClient; // Elasticsearch client

    /**
     * Unified search for customer data.
     * 
     * @param customerName The customer's name to search for.
     * @return UnifiedSearchResponseDTO containing data from PostgreSQL and Neo4j.
     */
    public UnifiedSearchResponseDTO searchCustomer(String customerName) throws Exception {
        // Step 1: Search Elasticsearch for quick match
        SearchRequest searchRequest = new SearchRequest("customers"); // Elasticsearch index
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", customerName));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

        // Extract customer ID from Elasticsearch result
        String customerId = extractCustomerIdFromElasticsearch(searchResponse);

        // Step 2: Fetch detailed KYC and transaction data from PostgreSQL
        KYCDataDTO kycData = postgreSQLRepository.getKYCDataByCustomerId(customerId);
        List<TransactionDataDTO> transactions = postgreSQLRepository.getTransactionsByCustomerId(customerId);

        // Step 3: Fetch relationship data from Neo4j
        List<RelationshipDataDTO> relationships = neo4jRepository.getRelationshipsByCustomerId(customerId);

        // Step 4: Combine data into a unified response
        UnifiedSearchResponseDTO response = new UnifiedSearchResponseDTO();
        response.setKycData(kycData);
        response.setTransactions(transactions);
        response.setRelationships(relationships);

        return response;
    }

    private String extractCustomerIdFromElasticsearch(SearchResponse searchResponse) {
        // Extract the first matched customer ID from Elasticsearch results
        return searchResponse.getHits().getHits()[0].getSourceAsMap().get("customerId").toString();
    }
}
```

---

#### **2. PostgreSQLRepository.java**
This repository handles PostgreSQL interactions.

```java
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class PostgreSQLRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public KYCDataDTO getKYCDataByCustomerId(String customerId) {
        String sql = "SELECT * FROM kyc WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{customerId}, (rs, rowNum) -> {
            KYCDataDTO kyc = new KYCDataDTO();
            kyc.setCustomerId(rs.getLong("customer_id"));
            kyc.setFullName(rs.getString("full_name"));
            kyc.setDateOfBirth(rs.getString("date_of_birth"));
            kyc.setNationality(rs.getString("nationality"));
            // Populate other KYC fields
            return kyc;
        });
    }

    public List<TransactionDataDTO> getTransactionsByCustomerId(String customerId) {
        String sql = "SELECT * FROM transactions WHERE customer_id = ?";
        return jdbcTemplate.query(sql, new Object[]{customerId}, (rs, rowNum) -> {
            TransactionDataDTO transaction = new TransactionDataDTO();
            transaction.setTransactionId(rs.getLong("transaction_id"));
            transaction.setTransactionAmount(rs.getDouble("transaction_amount"));
            transaction.setTransactionDateTime(rs.getString("transaction_date_time"));
            // Populate other transaction fields
            return transaction;
        });
    }
}
```

---

#### **3. Neo4jRepository.java**
This repository handles Neo4j interactions.

```java
import org.springframework.stereotype.Repository;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Neo4jRepository {

    private final Driver neo4jDriver;

    public Neo4jRepository(Driver neo4jDriver) {
        this.neo4jDriver = neo4jDriver;
    }

    public List<RelationshipDataDTO> getRelationshipsByCustomerId(String customerId) {
        List<RelationshipDataDTO> relationships = new ArrayList<>();
        try (Session session = neo4jDriver.session()) {
            String query = "MATCH (c:Customer)-[r:TRANSACTS_WITH]->(p:Person) WHERE c.customerId = $customerId RETURN p.name AS counterparty, r.transactionAmount AS amount";
            Result result = session.run(query, Map.of("customerId", customerId));
            while (result.hasNext()) {
                var record = result.next();
                RelationshipDataDTO relationship = new RelationshipDataDTO();
                relationship.setCounterparty(record.get("counterparty").asString());
                relationship.setTransactionAmount(record.get("amount").asDouble());
                relationships.add(relationship);
            }
        }
        return relationships;
    }
}
```

---

#### **4. UnifiedSearchResponseDTO.java**
This DTO combines results from PostgreSQL and Neo4j.

```java
import java.util.List;

public class UnifiedSearchResponseDTO {
    private KYCDataDTO kycData;
    private List<TransactionDataDTO> transactions;
    private List<RelationshipDataDTO> relationships;

    // Getters and Setters
}
```

---

### **Benefits of This Approach**
1. **Unified Access**: Combines relational (PostgreSQL) and graph-based (Neo4j) data in a single response.
2. **Real-Time Search**: Elasticsearch provides fast customer search capabilities, even with large datasets.
3. **Decoupled Responsibilities**: PostgreSQL handles structured data; Neo4j focuses on relationships, while Elasticsearch optimizes search and analytics.

