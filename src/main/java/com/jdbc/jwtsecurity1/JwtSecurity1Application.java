package com.jdbc.jwtsecurity1;

import com.jdbc.jwtsecurity1.entity.Role;
import com.jdbc.jwtsecurity1.entity.User;

import com.jdbc.jwtsecurity1.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class JwtSecurity1Application {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurity1Application.class, args);
    }
    @Bean
    CommandLineRunner run(UserService userService) {
        return arg -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "admin", "admin", "admin" , false, new ArrayList<>()));
            userService.saveUser(new User(null, "user1", "user1", "user1", false, new ArrayList<>()));
            userService.saveUser(new User(null, "user2", "user2", "user2",false,  new ArrayList<>()));


            userService.addRoleToUser("admin", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("user1", "ROLE_USER");
            userService.addRoleToUser("user2", "ROLE_USER");

        };
    }
}



