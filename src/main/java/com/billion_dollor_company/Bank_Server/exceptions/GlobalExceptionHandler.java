package com.billion_dollor_company.Bank_Server.exceptions;

import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.CheckBalanceFailedException;
import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.DataNotFoundException;
import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.TransactionFailedException;
import com.billion_dollor_company.Bank_Server.payloads.StatusResDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StatusResDTO> resourceNotFoundException(DataNotFoundException exception) {
        String message = exception.getMessage();
        StatusResDTO responseInfo = new StatusResDTO(Constants.Values.FAILED, message);
        return new ResponseEntity<>(responseInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionFailedException.class)
    public ResponseEntity<TransactionResDTO> transactionFailedException(TransactionFailedException exception) {
        String message = exception.getMessage();
        TransactionResDTO responseInfo = new TransactionResDTO(Constants.Values.FAILED, message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_XML)
                .body(responseInfo);
    }

    @ExceptionHandler(CheckBalanceFailedException.class)
    public ResponseEntity<BalanceResDTO> transactionFailedException(CheckBalanceFailedException exception) {
        String message = exception.getMessage();
        BalanceResDTO responseInfo = BalanceResDTO.builder()
                .status(Constants.Values.FAILED)
                .message(message)
                .upiID(exception.getUpiID())
                .balance("-")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_XML)
                .body(responseInfo);
    }

}
