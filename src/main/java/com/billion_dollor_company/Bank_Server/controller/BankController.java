package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.Bank_Server.service.interfaces.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bank", produces = {"application/xml"})
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/checkBalance")
    public ResponseEntity<BalanceResDTO> getAccountBalance(@RequestBody BalanceReqDTO request) {
        System.out.println("The request at bank is "+request);

        return ResponseEntity.ok(bankService.getAccountBalance(request));
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResDTO> initiateTransaction(@RequestBody TransactionReqDTO request) {
        return ResponseEntity.ok(bankService.initiateTransaction(request));
    }
}






