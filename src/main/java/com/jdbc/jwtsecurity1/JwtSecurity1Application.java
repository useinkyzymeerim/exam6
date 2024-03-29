package com.jdbc.jwtsecurity1;

import com.jdbc.jwtsecurity1.entity.Role;
import com.jdbc.jwtsecurity1.entity.User;
import com.jdbc.jwtsecurity1.enums.Status;
import com.jdbc.jwtsecurity1.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class JwtSecurity1Application {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurity1Application.class, args);
    }

    CommandLineRunner run(UserService userService) {
        return arg -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Meerim", "meerim", "1234", Status.Active, new ArrayList<>()));
            userService.saveUser(new User(null, "Aijan", "aijan", "5678", Status.Removed, new ArrayList<>()));
            userService.saveUser(new User(null, "Masha", "masha", "234", Status.Inactive, new ArrayList<>()));


            userService.addRoleToUser("Meerim", "ROLE_USER");
            userService.addRoleToUser("Aijan", "ROLE_USER");
            userService.addRoleToUser("Masha", "ROLE_SUPER_ADMIN");

        };
    }
}



