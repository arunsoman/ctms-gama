package com.gama.whiv.customerrepository.repository;

import com.gama.whiv.customerrepository.entities.KYCData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KYCDataRepository extends JpaRepository<KYCData, Long> {
    List<KYCData> findByFiId(String fiId);
    KYCData findByCustomerId(Long customerId);
    List<KYCData> findByCreatedAtBetween(String startTime, String endTime);
}
