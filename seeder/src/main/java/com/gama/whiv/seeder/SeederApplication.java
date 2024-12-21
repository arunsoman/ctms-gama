package com.gama.whiv.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.gama.whiv.rbac.entity.Permission;
import com.gama.whiv.rbac.entity.Role;
import com.gama.whiv.rbac.entity.User;
import com.gama.whiv.rbac.repository.PermissionRepository;
import com.gama.whiv.rbac.repository.RoleRepository;
import com.gama.whiv.rbac.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeederApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        return args -> {
            // Create permissions
            Permission createUserPermission = new Permission();
            createUserPermission.setName("create_user");
            permissionRepository.save(createUserPermission);

            // Create roles
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            Set<Permission> permissions = new HashSet<>();
            permissions.add(createUserPermission);
            adminRole.setPermissions(permissions);
            roleRepository.save(adminRole);

            // Create system user
            User systemUser = new User();
            systemUser.setUsername("system");
            systemUser.setPassword(new BCryptPasswordEncoder().encode("password"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            systemUser.setRoles(roles);
            userRepository.save(systemUser);
        };
    }
}
