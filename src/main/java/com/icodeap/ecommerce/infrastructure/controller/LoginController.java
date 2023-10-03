package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.Loginservice;
import com.icodeap.ecommerce.infrastructure.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    private final Loginservice loginservice;

    public LoginController(Loginservice loginservice) {
        this.loginservice = loginservice;
    }

    @GetMapping()
    public String login(){
        return "login";
    }
    @PostMapping
    public String access(UserDto userDto, HttpSession httpSession){
        userDto.setEmail(userDto.getUserName());
        log.info("userDto email:{}", userDto.getEmail());
        log.info("userDto pass:{}", userDto.getPassword());
        if (loginservice.existUser(userDto)){
            httpSession.setAttribute("iduser",loginservice.getUserId(userDto.getEmail()));
            if (loginservice.getUserType(userDto).name().equals("ADMIN")){
                return "redirect:/admin";
            }else{
                return "redirect:/home";
            }
        }

        return "redirect:/home";
    }
}
