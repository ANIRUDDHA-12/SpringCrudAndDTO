package com.firstProjectDemo.first_api;

public class MethodArgumentNotValidException extends RuntimeException{
    public MethodArgumentNotValidException(String message){
        super(message);
    }
}
