package com.gama.whiv.user.repository;

import com.gama.whiv.user.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    public   List<User>  findByUsernameContaining(String query);
}
