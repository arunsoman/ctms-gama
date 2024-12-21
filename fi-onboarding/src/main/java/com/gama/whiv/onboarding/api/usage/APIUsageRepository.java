package com.gama.whiv.onboarding.api.usage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APIUsageRepository extends JpaRepository<APIUsage, Long> {
    List<APIUsage> findAllByFiId(Long fiId);
}
