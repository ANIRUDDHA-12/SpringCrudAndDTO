package com.firstProjectDemo.first_api.controller;

import com.firstProjectDemo.first_api.AppUser;
import com.firstProjectDemo.first_api.AppUserRepository;
import com.firstProjectDemo.first_api.model.JwtAuthenticationFilterLibrary;
import com.firstProjectDemo.first_api.service.JwtServiceLibrary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library")
public class AuthControllerLibrary {
    private final JwtServiceLibrary jwtServiceLibrary;
    private final AppUserRepository appUserRepository;
    private final  PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthControllerLibrary(
            JwtServiceLibrary jwtServiceLibrary,
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ){
        this.appUserRepository=appUserRepository;
        this.jwtServiceLibrary=jwtServiceLibrary;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AppUser appUser){
        if(appUserRepository.findByUserName(appUser.getUserName()).isPresent()){
            return new ResponseEntity("Username already taken", HttpStatus.BAD_REQUEST);
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
        return new ResponseEntity<>("Saved successfully",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AppUser appUser){
        Authentication authentication=authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(appUser.getUserName(),appUser.getPassword()));

        if(authentication.isAuthenticated()){
            String token= jwtServiceLibrary.generateToken(appUser.getUserName());
            return new ResponseEntity(token,HttpStatus.CREATED);
        }
        else{
            throw new RuntimeException("Error creating");
        }
    }

}
