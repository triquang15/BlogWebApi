package com.devteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);

}
