package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionResponseInfo;
import com.billion_dollor_company.Bank_Server.service.interfaces.BankService;
import com.billion_dollor_company.Bank_Server.service.interfaces.PSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/psp")
public class PSPController {

    @Autowired
    private PSPService pspService;

    @Autowired
    private BankService bankService;

    @RequestMapping("/transaction")
    public ResponseEntity<TransactionResponseInfo> initiateTransaction(@RequestBody TransactionRequestInfo request) {
        return pspService.initiateTransaction(request);
    }

    @RequestMapping("/userInfo")
    public ResponseEntity<AccountInfo> getUserInfo(@RequestBody AccountInfo request) {

        AccountInfo userAccountInfo = pspService.getUserInfo(request);
        if (userAccountInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userAccountInfo);
    }

}

