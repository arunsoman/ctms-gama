package com.example.entityresolution.controller;

import com.example.entityresolution.model.Entity;
import com.example.entityresolution.model.Relationship;
import com.example.entityresolution.service.EntityResolutionService;
import com.example.entityresolution.service.RelationshipMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/er-matching")
public class ERMatchingController {

    @Autowired
    private EntityResolutionService entityResolutionService;

    @Autowired
    private RelationshipMapperService relationshipMapperService;

    /**
     * Submit entities for matching and resolution.
     *
     * @param entities List of entities to process.
     * @return List of resolved entities.
     *
     * **Scenario**: 
     * Use this endpoint when a batch of customer or account records from multiple sources needs to be deduplicated 
     * and resolved into unified profiles.
     *
     * Sample Input:
     * [
     *   {"entityId": "123", "type": "CUSTOMER", "attributes": {"name": "John Doe", "address": "123 Elm St"}},
     *   {"entityId": "456", "type": "CUSTOMER", "attributes": {"name": "J. Doe", "address": "123 Elm Street"}}
     * ]
     *
     * Sample Output:
     * [
     *   {"entityId": "123", "type": "CUSTOMER", "attributes": {"name": "John Doe", "address": "123 Elm Street"}, "riskScore": 95.0}
     * ]
     */
    @PostMapping("/submit-entities")
    public ResponseEntity<List<Entity>> submitEntities(@RequestBody List<Entity> entities) {
        List<Entity> resolvedEntities = entityResolutionService.resolveEntities(entities);
        return ResponseEntity.ok(resolvedEntities);
    }

    /**
     * Retrieve all resolved entities.
     *
     * @return List of all resolved entities.
     *
     * **Scenario**:
     * Use this endpoint to retrieve a list of all entities that have been processed and resolved. 
     * Typically called by investigators or analysts reviewing resolved profiles.
     *
     * Sample Output:
     * [
     *   {"entityId": "123", "type": "CUSTOMER", "attributes": {"name": "John Doe", "address": "123 Elm Street"}, "riskScore": 95.0},
     *   {"entityId": "789", "type": "ACCOUNT", "attributes": {"accountNumber": "111111", "balance": "5000"}, "riskScore": 80.0}
     * ]
     */
    @GetMapping("/resolved-entities")
    public ResponseEntity<List<Entity>> getAllResolvedEntities() {
        List<Entity> resolvedEntities = entityResolutionService.getResolvedEntities();
        return ResponseEntity.ok(resolvedEntities);
    }

    /**
     * Get a unified profile for a specific entity by ID.
     *
     * @param entityId The ID of the entity to retrieve.
     * @return Unified entity profile.
     *
     * **Scenario**:
     * Use this endpoint to retrieve detailed information about a specific entity by its ID. 
     * Ideal when investigating a particular person, account, or organization flagged in AML monitoring.
     *
     * Sample Input: /api/er-matching/entity/123
     *
     * Sample Output:
     * {
     *   "entityId": "123",
     *   "type": "CUSTOMER",
     *   "attributes": {"name": "John Doe", "address": "123 Elm Street"},
     *   "riskScore": 95.0
     * }
     */
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<Entity> getUnifiedEntity(@PathVariable String entityId) {
        Entity entity = entityResolutionService.getUnifiedEntity(entityId);
        return ResponseEntity.ok(entity);
    }

    /**
     * Retrieve relationships for a specific entity by ID.
     *
     * @param entityId The ID of the entity.
     * @return List of relationships involving the entity.
     *
     * **Scenario**:
     * Use this endpoint to identify all entities directly or indirectly connected to a given entity.
     * Helpful for network analysis in fraud detection or money laundering investigations.
     *
     * Sample Input: /api/er-matching/entity/123/relationships
     *
     * Sample Output:
     * [
     *   {"relationshipId": "1", "source": {"entityId": "123"}, "target": {"entityId": "456"}, "type": "SHARED_ADDRESS", "strength": 85.0}
     * ]
     */
    @GetMapping("/entity/{entityId}/relationships")
    public ResponseEntity<List<Relationship>> getEntityRelationships(@PathVariable String entityId) {
        List<Relationship> relationships = relationshipMapperService.getRelationshipsForEntity(entityId);
        return ResponseEntity.ok(relationships);
    }

    /**
     * Retrieve all relationships in the system.
     *
     * @return List of all relationships.
     *
     * **Scenario**:
     * Use this endpoint to retrieve a global view of all relationships across entities. 
     * Useful for generating relationship graphs or overall network analysis.
     *
     * Sample Output:
     * [
     *   {"relationshipId": "1", "source": {"entityId": "123"}, "target": {"entityId": "456"}, "type": "SHARED_ADDRESS", "strength": 85.0},
     *   {"relationshipId": "2", "source": {"entityId": "123"}, "target": {"entityId": "789"}, "type": "TRANSACTION", "strength": 92.0}
     * ]
     */
    @GetMapping("/relationships")
    public ResponseEntity<List<Relationship>> getAllRelationships() {
        List<Relationship> relationships = relationshipMapperService.getAllRelationships();
        return ResponseEntity.ok(relationships);
    }

    /**
     * Retrieve suspected duplicate entities based on matching criteria.
     *
     * @param entityId The ID of the entity to find duplicates for.
     * @return List of suspected duplicates.
     *
     * **Scenario**:
     * Use this endpoint to identify potential duplicates for an entity in cases of data discrepancies or inconsistencies.
     *
     * Sample Input: /api/er-matching/duplicates/123
     *
     * Sample Output:
     * [
     *   {"entityId": "456", "type": "CUSTOMER", "attributes": {"name": "J. Doe", "address": "123 Elm Street"}, "riskScore": 90.0}
     * ]
     */
    @GetMapping("/duplicates/{entityId}")
    public ResponseEntity<List<Entity>> findDuplicateEntities(@PathVariable String entityId) {
        List<Entity> duplicates = entityResolutionService.findDuplicates(entityId);
        return ResponseEntity.ok(duplicates);
    }

    /**
     * Add a new rule to the matching system.
     *
     * @param ruleName The name of the rule.
     * @param description The description of the rule.
     * @return Success message.
     *
     * **Scenario**:
     * Use this endpoint when new business or regulatory requirements necessitate adding custom rules 
     * for entity matching or resolution.
     *
     * Sample Input:
     * POST /api/er-matching/add-rule?ruleName=SharedAddress&description=Match entities sharing the same address.
     *
     * Sample Output:
     * "Matching rule added successfully"
     */
    @PostMapping("/add-rule")
    public ResponseEntity<String> addMatchingRule(
            @RequestParam String ruleName,
            @RequestParam String description
    ) {
        entityResolutionService.addResolutionRule(ruleName, description);
        return ResponseEntity.ok("Matching rule added successfully");
    }

    /**
     * Trigger a system-wide relationship mapping operation.
     *
     * @return Number of relationships mapped.
     *
     * **Scenario**:
     * Use this endpoint to initiate a relationship mapping operation across all entities in the system. 
     * Typically called after a bulk data ingestion or resolution update.
     *
     * Sample Output:
     * 42
     */
    @PostMapping("/map-relationships")
    public ResponseEntity<Integer> mapRelationships() {
        int mappedCount = relationshipMapperService.mapAllRelationships();
        return ResponseEntity.ok(mappedCount);
    }
}
