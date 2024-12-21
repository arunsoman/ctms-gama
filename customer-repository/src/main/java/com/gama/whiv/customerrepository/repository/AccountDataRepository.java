package com.gama.whiv.customerrepository.repository;

import com.gama.whiv.customerrepository.entities.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDataRepository extends JpaRepository<AccountData, Long> {
    List<AccountData> findByFiId(String fiId);
    List<AccountData> findByCustomerId(Long customerId);
    List<AccountData> findByCreatedAtBetween(String startTime, String endTime);
}
