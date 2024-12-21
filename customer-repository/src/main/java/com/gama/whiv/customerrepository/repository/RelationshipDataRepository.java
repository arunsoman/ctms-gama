package com.gama.whiv.customerrepository.repository;

import com.gama.whiv.customerrepository.entities.RelationshipData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipDataRepository extends JpaRepository<RelationshipData, Long> {
    List<RelationshipData> findByCustomerId(Long customerId);
}
