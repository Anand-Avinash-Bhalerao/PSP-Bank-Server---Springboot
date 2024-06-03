package com.billion_dollor_company.Bank_Server.exceptions;

import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.*;
import com.billion_dollor_company.Bank_Server.payloads.StatusResDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getErrorMessage(BindingResult errors) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        int counter = 0;
        final int noOfErrors = errors.getErrorCount();
        for (ObjectError error : errors.getAllErrors()) {
            counter++;
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMessageBuilder.append(fieldName).append(" : ").append(errorMessage);
            if (counter != noOfErrors) errorMessageBuilder.append(" | ");
        }
        return errorMessageBuilder.toString();
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StatusResDTO> resourceNotFoundException(DataNotFoundException exception) {
        String message = exception.getMessage();
        StatusResDTO responseInfo = new StatusResDTO(Constants.Status.FAILED, message);
        return new ResponseEntity<>(responseInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionFailedException.class)
    public ResponseEntity<TransactionResDTO> transactionFailedException(TransactionFailedException exception) {
        String message = exception.getMessage();
        TransactionResDTO responseInfo = new TransactionResDTO(Constants.Status.FAILED, message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_XML)
                .body(responseInfo);
    }


    @ExceptionHandler(CheckBalanceFailedException.class)
    public ResponseEntity<BalanceResDTO> transactionFailedException(CheckBalanceFailedException exception) {
        String message = exception.getMessage();
        BalanceResDTO responseInfo = BalanceResDTO.builder()
                .status(Constants.Status.FAILED)
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_XML)
                .body(responseInfo);
    }

    @ExceptionHandler(AccountBasicRequestException.class)
    public ResponseEntity<StatusResDTO> errorsInAccountReqVariablesException(AccountBasicRequestException exception) {
        String errorMessageBuilder = getErrorMessage(exception.getErrors());
        StatusResDTO response = StatusResDTO.builder().status(Constants.Status.FAILED).message(errorMessageBuilder).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CheckBalanceRequestException.class)
    public ResponseEntity<BalanceResDTO> errorsInCheckBalanceReqVariablesException(CheckBalanceRequestException exception) {
        String errorMessageBuilder = getErrorMessage(exception.getErrors());
        BalanceResDTO response = BalanceResDTO.builder().status(Constants.Status.FAILED).message(errorMessageBuilder).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TransactionRequestException.class)
    public ResponseEntity<TransactionResDTO> errorsInTransactionReqVariablesException(TransactionRequestException exception) {
        String errorMessageBuilder = getErrorMessage(exception.getErrors());
        TransactionResDTO response = TransactionResDTO.builder().status(Constants.Status.FAILED).message(errorMessageBuilder).build();
        return ResponseEntity.badRequest().body(response);
    }

}
