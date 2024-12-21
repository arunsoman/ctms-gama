package com.gama.whiv.customerrepository.repository;

import com.gama.whiv.customerrepository.entities.TransactionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDataRepository extends JpaRepository<TransactionData, Long> {
    List<TransactionData> findByFiId(String fiId);
    List<TransactionData> findByCustomerId(Long customerId);
    List<TransactionData> findByCreatedAtBetween(String startTime, String endTime);
    List<TransactionData> findRealTimeAnomalies();
}
