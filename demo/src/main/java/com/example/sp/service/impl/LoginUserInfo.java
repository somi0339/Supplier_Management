package com.example.sp.service.impl;
import com.example.sp.jpa.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class LoginUserInfo implements UserDetailsService {
    @Autowired
    public LoginUserRepository loginUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return loginUserRepository
                .findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + username
                        ));
    }
    }