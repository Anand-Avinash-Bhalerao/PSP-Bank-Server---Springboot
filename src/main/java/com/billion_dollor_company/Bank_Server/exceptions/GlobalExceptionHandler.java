package com.billion_dollor_company.Bank_Server.exceptions;

import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.DataNotFoundException;
import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.TransactionFailedException;
import com.billion_dollor_company.Bank_Server.payloads.StatusResponseDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseDTO;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StatusResponseDTO> resourceNotFoundException(DataNotFoundException exception) {
        String message = exception.getMessage();
        StatusResponseDTO responseInfo = new StatusResponseDTO(Constants.Values.FAILED, message);
        return new ResponseEntity<>(responseInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionFailedException.class)
    public ResponseEntity<TransactionResponseDTO> transactionFailedException(TransactionFailedException exception) {
        String message = exception.getMessage();
        TransactionResponseDTO responseInfo = new TransactionResponseDTO(Constants.Values.FAILED, message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_XML)
                .body(responseInfo);
    }

}
