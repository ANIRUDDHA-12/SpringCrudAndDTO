package com.firstProjectDemo.first_api;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository){
        this.appUserRepository=appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        AppUser appUser=appUserRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(appUser.getUserName())
                .password(appUser.getPassword())
                .roles(appUser.getRole())
                .build();

    }
}
