package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.Bank_Server.service.interfaces.NpciApiService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NpciApiServiceImpl implements NpciApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public NpciApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public BalanceResDTO getAccountBalance(BalanceReqDTO requestInfo) {
        String checkBalanceURL = Constants.Servers.NPCI_Server.getAccountBalanceURL();
        System.out.println("the url is " + checkBalanceURL);
        System.out.println("The req object sent to npci is "+requestInfo);
        // The exception handling is to handle response's other than status code of 200. if we receive BAD_REQUEST from NPCI, then we need to handle it.
        try {
            return restTemplate.postForEntity(checkBalanceURL, requestInfo, BalanceResDTO.class).getBody();
        } catch (HttpClientErrorException exception) {
            // The body contains an object of CheckBalanceDTO in XML form.
            return exception.getResponseBodyAs(BalanceResDTO.class);
        }
    }

    // To make an API call to NPCI.
    @Override
    public TransactionResDTO initiateTransaction(TransactionReqDTO requestInfo) {
        String transactionURL = Constants.Servers.NPCI_Server.getTransactionURL();

        // The exception handling is to handle response's other than status code 200. if we receive BAD_REQUEST from NPCI, then we need to handle it.
        try {
            return restTemplate.postForEntity(transactionURL, requestInfo, TransactionResDTO.class).getBody();
        } catch (HttpClientErrorException exception) {
            // The body contains an object of TransactionResponseDTO in XML form.
            return exception.getResponseBodyAs(TransactionResDTO.class);
        }
    }


}
