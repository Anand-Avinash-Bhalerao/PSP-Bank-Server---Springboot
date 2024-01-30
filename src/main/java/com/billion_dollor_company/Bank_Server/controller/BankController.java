package com.billion_dollor_company.Bank_Server.controller;


import com.billion_dollor_company.Bank_Server.models.ResponseStatusInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionRequest;
import com.billion_dollor_company.Bank_Server.service.AccountDetailsService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import com.billion_dollor_company.Bank_Server.util.Helper;
import com.billion_dollor_company.Bank_Server.util.cryptography.DecryptionManager;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;

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

            ResponseEntity<String> errorResponse = new ResponseEntity<>(errorResponseForNPCIBody, HttpStatus.OK);
            return errorResponse;
        }

        // now we have to decrypt the password using the bank's private key.
        String responseForNPCIStr;
        HttpStatus responseForNPCIStatus = HttpStatus.OK;
        try {

            DecryptionManager manager = new DecryptionManager(Constants.Keys.BANK_PRIVATE_KEY, "Bank decryption Key");
            String decryptedPassword = manager.getDecryptedMessage(transactionInfo.getEncryptedPassword());

            System.out.println("The Decrypted password at Bank is " + decryptedPassword + "\n");

            MessageDigest obj = MessageDigest.getInstance("SHA-256");
            //use update() method for passing data to the created MessageDigest Object
            obj.update(decryptedPassword.getBytes());
            //use the digest() method for computing the message digest
            byte[] byteArray = obj.digest();
            System.out.println(byteArray);
            //convert the byte array in to Hex String format
            StringBuffer hexData = new StringBuffer();
            for (int i = 0; i < byteArray.length; i++) {
                hexData.append(Integer.toHexString(0xFF & byteArray[i]));
            }
            String hashedDecryptedPassword= hexData.toString();
            String correctPassword = Helper.getUserPasswordFromDb(transactionInfo.getPayerUpiID());

            System.out.println("the hashed pw == correct pw is : "+ hashedDecryptedPassword.equals(correctPassword));
            if (hashedDecryptedPassword.equals(correctPassword)) {
                responseForNPCIObj.setStatus(Constants.Transaction.Response.SUCCESS);
                //do the transaction.
            } else {
                responseForNPCIObj.setStatus(Constants.Transaction.Response.WRONG_PASSWORD);
                responseForNPCIStatus = HttpStatus.OK;
            }
            responseForNPCIStr = xmlMapper.writeValueAsString(responseForNPCIObj);
        } catch (Exception e) {
            System.out.println("Exception caught");
            responseForNPCIStr = "";
            responseForNPCIObj.setStatus(Constants.Transaction.Response.FAILED);
            responseForNPCIStatus = HttpStatus.OK;
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






