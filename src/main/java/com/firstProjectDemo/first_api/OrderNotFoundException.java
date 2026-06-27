package com.firstProjectDemo.first_api;

public class OrderNotFoundException extends RuntimeException {
public OrderNotFoundException(String message){
    super(message);
}
}
