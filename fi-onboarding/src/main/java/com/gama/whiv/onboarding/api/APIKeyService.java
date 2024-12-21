package com.gama.whiv.onboarding.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class APIKeyService {
    @Autowired
    private APIKeyRepository apiKeyRepository;

    public List<APIKey> getAllKeys() {
        return apiKeyRepository.findAll();
    }

    public APIKey getKeyById(Long id) {
        return apiKeyRepository.findById(id).orElse(null);
    }

    public APIKey saveKey(APIKey key) {
        return apiKeyRepository.save(key);
    }

    public void deleteKey(Long id) {
        apiKeyRepository.deleteById(id);
    }

    public List<APIKey> getAllKeysByFIId(String fiId) {
        return apiKeyRepository.findAllByFiId(fiId);
    }
}
