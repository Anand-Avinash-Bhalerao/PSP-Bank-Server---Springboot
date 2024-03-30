package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseDTO;
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

    // To make an API call to NPCI.
    @Override
    public TransactionResponseDTO initiateTransaction(TransactionRequestDTO requestInfo) {
        String npciServerUrl = Constants.Servers.NPCI_Server.getTransactionURL();

        // The exception handling is to handle response's other than status code 200. if the receive BAD_REQUEST from NPCI, then we need to handle it.
        try {
            return restTemplate.postForEntity(npciServerUrl, requestInfo, TransactionResponseDTO.class).getBody();
        } catch (HttpClientErrorException exception) {
            // The body contains an object of TransactionResponseDTO in XML form.
            return exception.getResponseBodyAs(TransactionResponseDTO.class);
        }
    }
}
