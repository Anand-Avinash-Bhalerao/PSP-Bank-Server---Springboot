package com.billion_dollor_company.Bank_Server.exceptions.customExceptions;

import org.springframework.validation.BindingResult;

public class CheckBalanceRequestException extends RuntimeException{
    private BindingResult errors;

    public CheckBalanceRequestException(BindingResult errors) {
        this.errors = errors;
    }

    public BindingResult getErrors() {
        return errors;
    }
}
