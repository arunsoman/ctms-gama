package com.gama.whiv.user;

import com.gama.whiv.user.entity.User;
import com.gama.whiv.user.entity.Role;
import com.gama.whiv.user.entity.Permission;
import com.gama.whiv.user.repository.UserRepository;
import com.gama.whiv.user.repository.RoleRepository;
import com.gama.whiv.user.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        existingUser.setUsername(user.getUsername());
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
//        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());

        userRepository.save(existingUser);
    }

    public void createRole(Role role) {
        if (roleRepository.findByName(role.getName()) != null) {
            throw new RuntimeException("Role already exists");
        }
        roleRepository.save(role);
    }

    public void updateRole(Long id, Role role) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        existingRole.setName(role.getName());
        existingRole.setPermissions(role.getPermissions());

        roleRepository.save(existingRole);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public void mapRoleToUser(Long roleId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public void removeRoleFromUser(Long roleId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    public void mapPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
        role.getPermissions().add(permission);
        roleRepository.save(role);
    }

    public void removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
        role.getPermissions().remove(permission);
        roleRepository.save(role);
    }

    public void createPermission(Permission permission) {
        if (permissionRepository.findByName(permission.getName()) != null) {
            throw new RuntimeException("Permission already exists");
        }
        permissionRepository.save(permission);
    }

    public void updatePermission(Long id, Permission permission) {
        Permission existingPermission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));

        existingPermission.setName(permission.getName());
        permissionRepository.save(existingPermission);
    }

    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    public User validateUser(String username, String password) {
        User user = userRepository.findByUsername(username).stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new EntityNotFoundException("Invalid credentials");
        }

        return user;
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByUsernameContaining(query);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}