package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.domain.User;

public class RegistrationService {

    private final UserService userService;

    public RegistrationService(UserService userService) {
        this.userService = userService;
    }
    public void register(User user){
        userService.createUser(user);

    }
}
