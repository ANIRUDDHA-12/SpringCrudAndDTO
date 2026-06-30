package com.firstProjectDemo.first_api;

public class BudgetExceededException extends RuntimeException {
    public BudgetExceededException(String message){
        super(message);
    }
}
