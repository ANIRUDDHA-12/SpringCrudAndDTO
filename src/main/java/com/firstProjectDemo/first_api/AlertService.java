package com.firstProjectDemo.first_api;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    public void sendWelcomeAlert(String userName){
        try{
            System.out.println("Welcome email sent to " + userName);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
