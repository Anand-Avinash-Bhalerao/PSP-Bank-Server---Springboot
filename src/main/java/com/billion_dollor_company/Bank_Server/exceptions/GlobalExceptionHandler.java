package com.billion_dollor_company.Bank_Server.exceptions;

import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.DataNotFoundException;
import com.billion_dollor_company.Bank_Server.payloads.StatusResponseInfo;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StatusResponseInfo> resourceNotFoundException(DataNotFoundException exception) {
        String message = exception.getMessage();
        StatusResponseInfo responseInfo = new StatusResponseInfo(Constants.Values.FAILED, message);
        return new ResponseEntity<>(responseInfo, HttpStatus.NOT_FOUND);
    }
}
