package com.firstProjectDemo.first_api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/public")
public class AuthController {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AppUserRepository appUserRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager){
        this.appUserRepository=appUserRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> uploadUser(@RequestBody AppUser appUser){
       if(appUserRepository.findByUserName(appUser.getUserName()).isPresent()){
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

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AppUser appUser){
        Authentication authentication= authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(appUser.getUserName(),appUser.getPassword())
        );
        if(authentication.isAuthenticated()){
            String token= jwtService.generateToken(appUser.getUserName());
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        else{
            throw new RuntimeException("Invalid access");
        }
    }
}
