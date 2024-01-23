package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.util.Constants;
import com.billion_dollor_company.Bank_Server.util.Helper;
import com.billion_dollor_company.Bank_Server.util.cryptography.DecryptionManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/bank")
public class BankController {

    private String getUserPassword(String asda){
        return "123456";
    } //comment

    @PostMapping("/transaction")
    public String initiateTransaction(@RequestBody String xmlRequest) {

        String[] toExtractArr = {Constants.TransactionRequest.AMOUNT, Constants.TransactionRequest.ENCRYPTED_STRING, Constants.TransactionRequest.BANK_NAME, Constants.TransactionRequest.PAYER_ID, Constants.TransactionRequest.PAYEE_ID};

        // in this requestMap, we extract all the tags from the request xml.
        HashMap<String, String> requestMap = Helper.xmlToMap(xmlRequest, toExtractArr);
        String bankName = requestMap.get(Constants.TransactionRequest.BANK_NAME);
        String payerID = requestMap.get(Constants.TransactionRequest.PAYER_ID);
        String payeeID = requestMap.get(Constants.TransactionRequest.PAYEE_ID);
        String amount = requestMap.get(Constants.TransactionRequest.AMOUNT);

        // This is the message sent from NPCI. Encrypted part is the password.
        String encryptedMessage = requestMap.get(Constants.TransactionRequest.ENCRYPTED_STRING);

        // in this responseMap, we store all the tags needed in for the response xml. Used in the very end.
        HashMap<String, String> responseMap = new HashMap<>();

        try {
            DecryptionManager manager = new DecryptionManager(Constants.Keys.BANK_PRIVATE_KEY, "Bank decryption Key");
            String decryptedPassword = manager.getDecryptedMessage(encryptedMessage);
            System.out.println("The decrypted string is " + decryptedPassword);
            String userPassword = Helper.getUserPassword(responseMap.get(Constants.TransactionRequest.PAYER_ID));

            boolean isSame = decryptedPassword.equals(userPassword);
            responseMap.put("status", isSame ? "success" : "failed");
        } catch (Exception e) {
            responseMap.put("status", "failed");
        }
        return Helper.mapToXML(responseMap, "response");
    }
}
