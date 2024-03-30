package com.billion_dollor_company.Bank_Server.exceptions.customExceptions;

public class CheckBalanceFailedException extends RuntimeException {
    String message;
    String upiID;
    public CheckBalanceFailedException(String upiID, String message) {
        super(message);
        this.upiID = upiID;
        this.message = message;
    }

    public String getUpiID() {
        return upiID;
    }
}