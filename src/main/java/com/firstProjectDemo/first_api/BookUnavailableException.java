package com.firstProjectDemo.first_api;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(String message){
        super(message);
    }
}
