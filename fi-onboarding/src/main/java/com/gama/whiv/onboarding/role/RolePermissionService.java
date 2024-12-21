package com.gama.whiv.onboarding.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public List<RolePermission> getAllPermissions() {
        return rolePermissionRepository.findAll();
    }

    public RolePermission getPermissionById(Long id) {
        return rolePermissionRepository.findById(id).orElse(null);
    }

    public RolePermission savePermission(RolePermission permission) {
        return rolePermissionRepository.save(permission);
    }

    public void deletePermission(Long id) {
        rolePermissionRepository.deleteById(id);
    }
}
