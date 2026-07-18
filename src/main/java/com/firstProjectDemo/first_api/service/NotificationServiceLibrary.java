package com.firstProjectDemo.first_api.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class NotificationServiceLibrary {

    public void sendWelcomeEmail(String username){
        System.out.println("Sending out email for it"+Thread.currentThread().getName());
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("welcome user"+username);
    }
}
