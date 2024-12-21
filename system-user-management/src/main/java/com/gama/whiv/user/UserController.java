package com.gama.whiv.user;

import com.gama.whiv.user.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    // User and Role Management
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        service.createUser(user);
        return ResponseEntity.ok("User created successfully.");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        service.updateUser(id, user);
        return ResponseEntity.ok("User updated successfully.");
    }

    @PutMapping("/users/{id}/activate")
    public ResponseEntity<String> activate(@PathVariable Long id) {
        service.updateUser(id, user);
        return ResponseEntity.ok("User updated successfully.");
    }
    @PutMapping("/users/{id}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        service.updateUser(id, user);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @PostMapping("/roles")
    public ResponseEntity<String> createRole(@RequestBody Role role) {
        service.createRole(role);
        return ResponseEntity.ok("Role created successfully.");
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<String> updateRole(@PathVariable Long id, @RequestBody Role role) {
        service.updateRole(id, role);
        return ResponseEntity.ok("Role updated successfully.");
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(service.getAllRoles());
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = service.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        service.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully.");
    }

    // Mapping a role to a user
    @PostMapping("/roles/{roleId}/mapto/{userId}")
    public ResponseEntity<?> mapRoleToUser(@PathVariable Long roleId, @PathVariable Long userId) {
        service.mapRoleToUser(roleId, userId);
        return ResponseEntity.ok().build();
    }

    // Removing a role from a user
    @DeleteMapping("/roles/{roleId}/removeto/{userId}")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable Long roleId, @PathVariable Long userId) {
        service.removeRoleFromUser(roleId, userId);
        return ResponseEntity.ok().build();
    }

    // Mapping a permission to a role
    @PostMapping("/roles/{roleId}/mappermission/{permissionId}")
    public ResponseEntity<?> mapPermissionToRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        service.mapPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok().build();
    }

    // Removing a permission from a role
    @DeleteMapping("/roles/{roleId}/removepermission/{permissionId}")
    public ResponseEntity<?> removePermissionFromRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        service.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/permissions")
    public ResponseEntity<String> createPermission(@RequestBody Permission permission) {
        service.createPermission(permission);
        return ResponseEntity.ok("Permission created successfully.");
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity<String> updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
        service.updatePermission(id, permission);
        return ResponseEntity.ok("Permission updated successfully.");
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        Permission permission = service.getPermissionById(id);
        return ResponseEntity.ok(permission);
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        service.deletePermission(id);
        return ResponseEntity.ok("Permission deleted successfully.");
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        List<User> users = service.searchUsers(query);
        return ResponseEntity.ok(users);
    }
}
