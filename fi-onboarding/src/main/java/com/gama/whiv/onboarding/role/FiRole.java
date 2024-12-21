package com.gama.whiv.onboarding.role;

import com.gama.whiv.onboarding.permission.FiPermission;
import com.gama.whiv.onboarding.user.FIUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Entity
@Getter
@Setter
@Table(name="fi_roles")
public class FiRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<FIUser> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "fi_role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<FiPermission> permissions;
}
