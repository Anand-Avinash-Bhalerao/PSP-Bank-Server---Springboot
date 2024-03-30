package com.billion_dollor_company.Bank_Server.exceptions.customExceptions;

public class DataNotFoundException extends RuntimeException {
    String message;

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
