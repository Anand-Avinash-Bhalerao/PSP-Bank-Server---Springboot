package com.billion_dollor_company.Bank_Server.exceptions.customExceptions;

import org.springframework.validation.BindingResult;

public class RegistrationRequestException extends RuntimeException{
    private BindingResult errors;

    public RegistrationRequestException(BindingResult errors) {
        this.errors = errors;
    }

    public BindingResult getErrors() {
        return errors;
    }
}
