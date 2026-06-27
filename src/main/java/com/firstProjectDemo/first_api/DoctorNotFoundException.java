package com.firstProjectDemo.first_api;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(String message){
        super(message);
    }
}
