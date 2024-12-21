package com.gama.whiv.customerrepository.repository;

import com.gama.whiv.customerrepository.entities.RiskAssessmentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskAssessmentDataRepository extends JpaRepository<RiskAssessmentData, Long> {
    RiskAssessmentData findByCustomerId(Long customerId);
}
