package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseInfo;
import com.billion_dollor_company.Bank_Server.service.interfaces.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bank", produces = {"application/xml"})
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponseInfo> initiateTransaction(@RequestBody TransactionRequestInfo request) {
        return ResponseEntity.ok(bankService.initiateTransaction(request));
    }
}






