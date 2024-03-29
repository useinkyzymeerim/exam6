package com.jdbc.jwtsecurity1.repo;

import com.jdbc.jwtsecurity1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}