package com.example.fintech.repository;

import com.example.fintech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrMobile(String email, String mobile);
    Optional<User> findByMobile(String mobile);
}