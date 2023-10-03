package com.icodeap.ecommerce.infrastructure.service;

import com.icodeap.ecommerce.application.service.LoginService;
import com.icodeap.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final LoginService loginService;
    private final  Integer USER_NOT_FOUND =0;

    @Autowired
    private HttpSession httpSession;

    public UserDetailsServiceImpl(LoginService loginservice) {
        this.loginService = loginservice;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Integer idUser= loginService.getUserId(username);

        if (idUser != USER_NOT_FOUND){
            User user = loginService.getUser(username);
            httpSession.setAttribute("iduser",user.getId());
            return org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).password(user.getPassword()).
                    roles(user.getUserType().name()).build();
        } else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }
}
