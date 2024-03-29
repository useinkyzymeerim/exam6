package com.jdbc.jwtsecurity1.repo;


import com.jdbc.jwtsecurity1.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}