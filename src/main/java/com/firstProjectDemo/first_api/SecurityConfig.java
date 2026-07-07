package com.firstProjectDemo.first_api;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(o->o.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers("/api/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails alice= User.builder()
//                .username("alice")
//                .password("{noop}password123")
//                .roles("Admin")
//                .build();
//
//        UserDetails bob = User.builder()
//                .username("bob")
//                .password("{noop}password1234")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(alice,bob);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        return new BCryptPasswordEncoder();
    }
}
