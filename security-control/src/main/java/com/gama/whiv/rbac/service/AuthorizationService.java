package com.gama.whiv.rbac.service;

import com.gama.whiv.rbac.entity.Role;
import com.gama.whiv.rbac.entity.User;
import com.gama.whiv.rbac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    public boolean hasAccess(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(authentication.getName());
            return user.getRoles().stream()
                    .anyMatch(r -> r.getName().equals(role));
        }
        return false;
    }
}
