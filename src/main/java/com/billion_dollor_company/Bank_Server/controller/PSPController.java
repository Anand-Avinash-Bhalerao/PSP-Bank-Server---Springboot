package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.AccountBasicRequestException;
import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.TransactionRequestException;
import com.billion_dollor_company.Bank_Server.models.ListKeysInfo;
import com.billion_dollor_company.Bank_Server.payloads.AccountBasicDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.listKeys.ListKeysReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.Bank_Server.service.interfaces.PSPService;
import com.billion_dollor_company.Bank_Server.service.interfaces.PublicKeysService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/psp", produces = {"application/json"})
public class PSPController {

    private final PSPService pspService;
    private final PublicKeysService publicKeysService;
    @Autowired
    public PSPController(PSPService pspService, PublicKeysService publicKeysService) {
        this.pspService = pspService;
        this.publicKeysService = publicKeysService;
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

    @GetMapping("/getListKeys")
    public List<ListKeysReqDTO> getListKeys() {
        // initiateTransaction forwards the req to bank by decrypting and re-encrypting the password.
        System.out.println("In PSP controller");
        List<ListKeysReqDTO> responseDTO= publicKeysService.getListKeys();

        // If the response status code was BAD_REQUEST then send Failed, 400 otherwise Success 200.
//        if (responseDTO.getStatus().equals(Constants.Status.FAILED)) {
//            return ResponseEntity.badRequest().body(responseDTO);
//        }
        return ResponseEntity.ok().body(responseDTO).getBody();
    }

}

