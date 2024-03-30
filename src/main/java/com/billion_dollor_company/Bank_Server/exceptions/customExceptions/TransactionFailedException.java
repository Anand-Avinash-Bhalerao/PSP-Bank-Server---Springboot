package com.billion_dollor_company.Bank_Server.exceptions.customExceptions;


public class TransactionFailedException extends RuntimeException {
    String message;

    public TransactionFailedException(String message) {
        super(message);
        this.message = message;
    }
}
