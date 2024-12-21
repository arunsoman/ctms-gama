package com.gama.whiv.onboarding.fi.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FIActivityLogService {
    @Autowired
    private FIActivityLogRepository fiActivityLogRepository;

    public List<FIActivityLog> getAllLogs() {
        return fiActivityLogRepository.findAll();
    }

    public FIActivityLog getLogById(Long id) {
        return fiActivityLogRepository.findById(id).orElse(null);
    }

    public FIActivityLog saveLog(FIActivityLog log) {
        return fiActivityLogRepository.save(log);
    }

    public void deleteLog(Long id) {
        fiActivityLogRepository.deleteById(id);
    }
}
