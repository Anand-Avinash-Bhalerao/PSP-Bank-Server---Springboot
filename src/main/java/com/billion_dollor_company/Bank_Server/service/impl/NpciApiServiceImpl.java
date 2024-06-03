package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.fetchKeys.FetchKeysReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.fetchKeys.FetchKeysResDTO;
import com.billion_dollor_company.Bank_Server.payloads.registration.RegistrationReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.registration.RegistrationResDTO;
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
        // The exception handling is to handle response's other than status code of 200. if we receive BAD_REQUEST from NPCI, then we need to handle it.
        try {
            return restTemplate.postForEntity(checkBalanceURL, requestInfo, BalanceResDTO.class).getBody();
        } catch (HttpClientErrorException exception) {
            // The body contains an object of CheckBalanceDTO in XML form.
            return exception.getResponseBodyAs(BalanceResDTO.class);
        }
    }

    @Override
    public RegistrationResDTO register(RegistrationReqDTO requestInfo) {

        String registrationURL = Constants.Servers.NPCI_Server.getRegistrationURL();

        try {
            return restTemplate.postForEntity(registrationURL, requestInfo, RegistrationResDTO.class).getBody();
        } catch (HttpClientErrorException exception) {
            // The body contains an object of TransactionResponseDTO in XML form.
            return exception.getResponseBodyAs(RegistrationResDTO.class);
        }
    }

    @Override
    public FetchKeysResDTO fetchKeys() {
        String fetchKeysURL = Constants.Servers.NPCI_Server.getFetchKeysURL();
        try {
            return restTemplate.getForEntity(fetchKeysURL, FetchKeysResDTO.class).getBody();
        } catch (HttpClientErrorException exception) {
            // The body contains an object of TransactionResponseDTO in XML form.
            return exception.getResponseBodyAs(FetchKeysResDTO.class);
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

    // 200 - 300 ok -> body -> 10 fields -> status, mesages,asd,as,d,as,das,d,,
    // post -> body -> 200, 400 -> body
    // 400 - bad request -> body -> 2 fields -> status, message
}
