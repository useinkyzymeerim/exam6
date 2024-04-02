package com.jdbc.jwtsecurity1.service;

import com.jdbc.jwtsecurity1.entity.Role;
import com.jdbc.jwtsecurity1.entity.User;
import jakarta.transaction.Status;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User saveUser(User user);


    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);
    void delete(long userId);
    List<User> getUsers();
    void blockUser(Long userId);
    void unblockUser(Long userId);
}
