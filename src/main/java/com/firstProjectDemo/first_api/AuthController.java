package com.firstProjectDemo.first_api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RestController
@RequestMapping("/api/appUser")
public class AuthController {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserRepository appUserRepository,PasswordEncoder passwordEncoder){
        this.appUserRepository=appUserRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/api/public/register")
    public ResponseEntity<String> uploadUser(@RequestBody AppUser appUser){
       if(appUserRepository.findByUsername(appUser.getUserName()).isPresent()){
           return new ResponseEntity<>("Error:username already taken", HttpStatus.BAD_REQUEST);
       }

       appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

       appUserRepository.save(appUser);
       return new ResponseEntity<>("Saved successfully",HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
