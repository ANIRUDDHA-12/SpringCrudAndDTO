package com.firstProjectDemo.first_api;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async
    public void sendProjectCreationEmail(String projectName){
        System.out.println("Starting background email on task thread"+Thread.currentThread().getName());
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Email successfully sent for project: " + projectName);
    }

}
