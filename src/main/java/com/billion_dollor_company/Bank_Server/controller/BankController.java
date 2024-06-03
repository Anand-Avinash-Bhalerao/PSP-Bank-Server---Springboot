package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.Bank_Server.service.interfaces.BankService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import com.billion_dollor_company.Bank_Server.util.MessagePrinter;
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

        // print the request on console.
        MessagePrinter.printMessage(Constants.MessagePrinter.Server.BANK, Constants.MessagePrinter.MethodType.CheckBalance, request);

        // from the service only successful account balance response reaches till here. so no need for exception handling
        return ResponseEntity.ok(bankService.getAccountBalance(request));
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResDTO> initiateTransaction(@RequestBody TransactionReqDTO request) {

        // print the request on console.
        MessagePrinter.printMessage(Constants.MessagePrinter.Server.BANK, Constants.MessagePrinter.MethodType.InitiateTransaction, request);

        // from the service only successful transaction response reaches till here. so no need for exception handling
        return ResponseEntity.ok(bankService.initiateTransaction(request));
    }
}






