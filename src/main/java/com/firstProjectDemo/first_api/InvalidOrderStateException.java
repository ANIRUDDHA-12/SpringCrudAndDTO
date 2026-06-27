package com.firstProjectDemo.first_api;

public class InvalidOrderStateException  extends RuntimeException{
    public InvalidOrderStateException(String message){
        super(message);
    }
}
