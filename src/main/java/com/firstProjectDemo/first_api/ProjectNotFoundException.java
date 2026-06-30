package com.firstProjectDemo.first_api;

public class ProjectNotFoundException  extends RuntimeException{
    public ProjectNotFoundException(String message){
        super(message);
    }
}
