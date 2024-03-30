package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.payloads.AccountBasicInfo;
import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseInfo;
import com.billion_dollor_company.Bank_Server.service.interfaces.BankService;
import com.billion_dollor_company.Bank_Server.service.interfaces.PSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/psp", produces = {"application/json"})
public class PSPController {

    @Autowired
    private PSPService pspService;

    @Autowired
    private BankService bankService;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponseInfo> initiateTransaction(@RequestBody TransactionRequestInfo request) {
        TransactionResponseInfo responseInfo = pspService.initiateTransaction(request);
        return ResponseEntity.ok(responseInfo);
    }

    @GetMapping("/accountInfo")
    public ResponseEntity<AccountBasicInfo> getUserInfo(@RequestBody AccountInfo request) {
        AccountBasicInfo userInfo = pspService.getAccountInfo(request);
        return ResponseEntity.ok(userInfo);
    }

}

