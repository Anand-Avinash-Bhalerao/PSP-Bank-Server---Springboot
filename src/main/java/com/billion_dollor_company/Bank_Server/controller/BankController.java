package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.models.ResponseStatusInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionRequest;
import com.billion_dollor_company.Bank_Server.service.AccountDetailsService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import com.billion_dollor_company.Bank_Server.util.Helper;
import com.billion_dollor_company.Bank_Server.util.cryptography.DecryptionManager;
import com.billion_dollor_company.Bank_Server.util.helpers.HelperXML;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private AccountDetailsService accountDetailsService;

    @PostMapping("/transaction")
    public ResponseEntity<String> initiateTransaction(@RequestBody String reqFromNPCI) {
        System.out.println("\n______________________________________AT BANK SERVER______________________________________\n");
        System.out.println("The XML request sent from NPCI to Bank Server is:\n" + Helper.getPrettyXML(reqFromNPCI, TransactionRequest.class) + "\n");

        XmlMapper xmlMapper = new XmlMapper();
        TransactionRequest transactionInfo = null;
        ResponseStatusInfo responseForNPCIObj = new ResponseStatusInfo();

        try {
            transactionInfo = xmlMapper.readValue(reqFromNPCI, TransactionRequest.class);
        } catch (Exception e) {
            System.out.println("An error occurred while converting request from XML");
            e.printStackTrace();

            responseForNPCIObj.setStatus(Constants.Values.ERROR_WHILE_CONVERSION);
            String errorResponseForNPCIBody = "";
            try {
                errorResponseForNPCIBody = xmlMapper.writeValueAsString(responseForNPCIObj);
            } catch (Exception ignored) {
            }

            ResponseEntity<String> errorResponse = new ResponseEntity<>(errorResponseForNPCIBody, HttpStatus.BAD_REQUEST);
            return errorResponse;
        }

        // now we have to decrypt the password using the bank's private key.
        String responseForNPCIStr;
        HttpStatus responseForNPCIStatus = HttpStatus.OK;
        try {

            DecryptionManager manager = new DecryptionManager(Constants.Keys.BANK_PRIVATE_KEY, "Bank decryption Key");
            String decryptedPassword = manager.getDecryptedMessage(transactionInfo.getEncryptedString());

            System.out.println("The Decrypted password at Bank is " + decryptedPassword + "\n");
            String correctPassword = Helper.getUserPassword(transactionInfo.getPayerID());
            if (decryptedPassword.equals(correctPassword)) {
                responseForNPCIObj.setStatus(Constants.Transaction.Response.SUCCESS);
            } else {
                responseForNPCIObj.setStatus(Constants.Transaction.Response.WRONG_PASSWORD);
                responseForNPCIStatus = HttpStatus.BAD_REQUEST;
            }
            responseForNPCIStr = xmlMapper.writeValueAsString(responseForNPCIObj);
        } catch (Exception e) {
            System.out.println("Exception caught");
            responseForNPCIStr = "";
            responseForNPCIObj.setStatus(Constants.Transaction.Response.FAILED);
            responseForNPCIStatus = HttpStatus.BAD_REQUEST;
            try {
                responseForNPCIStr = xmlMapper.writeValueAsString(responseForNPCIObj);
            } catch (Exception ignored) {
                System.out.println("caught here");
            }
        }
        System.out.println("The response sent back to NPCI from bank in XML is: \n"+Helper.getPrettyXML(responseForNPCIStr, ResponseStatusInfo.class));
        return new ResponseEntity<>(responseForNPCIStr, responseForNPCIStatus);
    }
}






