package com.gama.whiv.onboarding.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FIUserService {
    @Autowired
    private FIUserRepository fiUserRepository;

    public List<FIUser> getAllUsers(PageRequest pR) {
        return fiUserRepository.findAll();
    }

    public FIUser getUserById(Long id) {
        return fiUserRepository.findById(id).orElse(null);
    }

    public FIUser saveUser(FIUser user) {
        return fiUserRepository.save(user);
    }

    public void deleteUser(Long id) {
        fiUserRepository.deleteById(id);
    }
}
