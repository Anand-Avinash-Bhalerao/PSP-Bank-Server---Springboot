package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.payloads.AccountBasicDTO;
import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseDTO;
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
    public ResponseEntity<TransactionResponseDTO> initiateTransaction(@RequestBody TransactionRequestDTO request) {
        TransactionResponseDTO responseInfo = pspService.initiateTransaction(request);
        return ResponseEntity.ok(responseInfo);
    }

    @GetMapping("/accountInfo")
    public ResponseEntity<AccountBasicDTO> getUserInfo(@RequestBody AccountBasicDTO request) {
        AccountBasicDTO userInfo = pspService.getAccountInfo(request);
        return ResponseEntity.ok(userInfo);
    }

}

