package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseDTO;
import com.billion_dollor_company.Bank_Server.service.interfaces.NpciApiService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return restTemplate.postForEntity(npciServerUrl, requestInfo, TransactionResponseDTO.class).getBody();
    }
}
