package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.domain.User;
import com.icodeap.ecommerce.domain.UserType;
import com.icodeap.ecommerce.infrastructure.dto.UserDto;

public class Loginservice {

    private UserService userService;

    public Loginservice(UserService userService) {
        this.userService = userService;
    }

    public boolean existUser(UserDto userDto){
        try{
            User user = userService.findByEmail(userDto.getEmail());
        }catch (Exception e){
            return false;
        }

        return false;
    }

    public Integer getUserId(String email){
        try{
            return userService.findByEmail(email).getId();
        }catch(Exception e){
            return 0;
        }

    }

    public UserType getUserType(UserDto userDto){
        return userService.findByEmail(userDto.getEmail()).getUserType();
    }

    public User getUser(String email){
        try{
           return userService.findByEmail(email);
        }catch (Exception e){
         return new User();
        }
    }
}
