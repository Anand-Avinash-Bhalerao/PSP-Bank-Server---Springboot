package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.models.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionResponseInfo;
import com.billion_dollor_company.Bank_Server.service.interfaces.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/transaction")
    @ResponseBody
    public ResponseEntity<TransactionResponseInfo> initiateTransaction(@RequestBody TransactionRequestInfo request) {
        System.out.println("The request at bank is: " + request);
        return bankService.initiateTransaction(request);
    }
}






