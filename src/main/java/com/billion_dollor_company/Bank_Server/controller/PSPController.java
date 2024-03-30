package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.payloads.AccountBasicDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseDTO;
import com.billion_dollor_company.Bank_Server.service.interfaces.PSPService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/psp", produces = {"application/json"})
public class PSPController {

    private final PSPService pspService;

    @Autowired
    public PSPController(PSPService pspService) {
        this.pspService = pspService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponseDTO> initiateTransaction(@RequestBody TransactionRequestDTO request) {
        TransactionResponseDTO responseInfo = pspService.initiateTransaction(request);

        // Check if the transaction status was SUCCESS or FAILED. if failed then send 400 Bad Request otherwise 200 OK.
        if (responseInfo.getStatus().equals(Constants.Transaction.Status.FAILED)) {
            return ResponseEntity.badRequest().body(responseInfo);
        }
        return ResponseEntity.ok().body(responseInfo);
    }

    @GetMapping("/accountInfo")
    public ResponseEntity<AccountBasicDTO> getUserInfo(@RequestBody AccountBasicDTO request) {
        AccountBasicDTO userInfo = pspService.getAccountInfo(request);

        // We have not done exception handling here because if it userInfo reaches till here, then it is valid.
        // If the record does not exist an exception gets thrown in the PSPServiceImpl class.
        // Exception then gets handled in GlobalExceptionHandler.
        return ResponseEntity.ok(userInfo);
    }

}

