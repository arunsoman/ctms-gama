package com.gama.whiv.user.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Column(name = "first_name")
    private String firstName;   

    @Column(name = "last_name")
    private String lastName;    

    @Column(name = "email")
    private String email;


    @Column(name = "is_active")
    private boolean active;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
