package com.gama.whiv.onboarding.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APIKeyRepository extends JpaRepository<APIKey, Long> {
    List<APIKey> findAllByFiId(String fiId);
}
