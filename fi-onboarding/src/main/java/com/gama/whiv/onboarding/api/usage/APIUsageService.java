package com.gama.whiv.onboarding.api.usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class APIUsageService {
    @Autowired
    private APIUsageRepository apiUsageRepository;

    public List<APIUsage> getAllUsages() {
        return apiUsageRepository.findAll();
    }

    public APIUsage getUsageById(Long id) {
        return apiUsageRepository.findById(id).orElse(null);
    }

    public APIUsage saveUsage(APIUsage usage) {
        return apiUsageRepository.save(usage);
    }

    public void deleteUsage(Long id) {
        apiUsageRepository.deleteById(id);
    }

    public List<APIUsage> getAllUsagesByFIId(String fiId) {
        return apiUsageRepository.findAllByFiId(Long.valueOf(fiId));
    }
}
