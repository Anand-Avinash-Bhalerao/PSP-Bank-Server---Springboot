package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionResponseInfo;
import com.billion_dollor_company.Bank_Server.service.interfaces.BankService;
import com.billion_dollor_company.Bank_Server.service.interfaces.PSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/psp",produces = {"application/json"})
public class PSPController {

    @Autowired
    private PSPService pspService;

    @Autowired
    private BankService bankService;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponseInfo> initiateTransaction(@RequestBody TransactionRequestInfo request) {
        return ResponseEntity.ok(pspService.initiateTransaction(request));
    }

    @GetMapping("/userInfo")
    public ResponseEntity<AccountInfo> getUserInfo(@RequestBody AccountInfo request) {
        return ResponseEntity.ok(pspService.getUserInfo(request));
    }

}

