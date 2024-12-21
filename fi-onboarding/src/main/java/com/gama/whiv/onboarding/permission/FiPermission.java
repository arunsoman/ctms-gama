package com.gama.whiv.onboarding.permission;


import com.gama.whiv.onboarding.role.FiRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Entity
@Getter
@Setter
@Table(name="fi_permissions")
public class FiPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String permissionName;

    @ManyToMany(mappedBy = "permissions")
    private Set<FiRole> roles;
}