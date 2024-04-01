package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.AccountBasicRequestException;
import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.TransactionRequestException;
import com.billion_dollor_company.Bank_Server.payloads.AccountBasicDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.Bank_Server.service.interfaces.PSPService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/psp", produces = {"application/json"})
public class PSPController {

    private final PSPService pspService;

    @Autowired
    public PSPController(PSPService pspService) {
        this.pspService = pspService;
    }

    // We have not done exception handling after making the service call because if userInfo reaches till here, then it is valid for sure.
    // If the record does not exist an exception gets thrown in the PSPServiceImpl class.
    // Exception then gets handled in GlobalExceptionHandler and not here.
    @PostMapping("/accountInfo")
    public ResponseEntity<AccountBasicDTO> getUserInfo(@Valid @RequestBody AccountBasicDTO request, BindingResult errors) {
        System.out.println("here");
        // to handle errors in the request sent from client.
        if(errors.hasErrors())
            throw new AccountBasicRequestException(errors);

        AccountBasicDTO userInfo = pspService.getAccountInfo(request);
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/checkBalance")
    public ResponseEntity<BalanceResDTO> getAccountBalance(@Valid @RequestBody BalanceReqDTO request, BindingResult errors) {

        if(errors.hasErrors())
            throw new AccountBasicRequestException(errors);

        BalanceResDTO responseInfo = pspService.getAccountBalance(request);

        // Check if the status was SUCCESS or FAILED. if failed then send 400 Bad Request otherwise 200 OK.
        if (responseInfo.getStatus().equals(Constants.Status.FAILED)) {
            return ResponseEntity.badRequest().body(responseInfo);
        }
        return ResponseEntity.ok().body(responseInfo);
    }


    @PostMapping("/transaction")
    public ResponseEntity<TransactionResDTO> initiateTransaction(@Valid @RequestBody TransactionReqDTO request, BindingResult errors) {

        if(errors.hasErrors())
            throw new TransactionRequestException(errors);

        TransactionResDTO responseInfo = pspService.initiateTransaction(request);

        // Check if the transaction status was SUCCESS or FAILED. if failed then send 400 Bad Request otherwise 200 OK.
        if (responseInfo.getStatus().equals(Constants.Status.FAILED)) {
            return ResponseEntity.badRequest().body(responseInfo);
        }
        return ResponseEntity.ok().body(responseInfo);
    }

}

