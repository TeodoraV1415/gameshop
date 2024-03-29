package com.softuni.gameshop.init;

import com.softuni.gameshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInit implements CommandLineRunner {
    private final UserService userService;

    public AdminInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.getByUsername("admin").isEmpty()) {
            this.userService.addAdmin();
        }
    }
}
