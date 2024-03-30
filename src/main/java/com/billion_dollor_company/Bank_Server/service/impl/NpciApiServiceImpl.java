package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseInfo;
import com.billion_dollor_company.Bank_Server.service.interfaces.NpciApiService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NpciApiServiceImpl implements NpciApiService {

    @Autowired
    private RestTemplate restTemplate;

    // To make an API call to NPCI.
    @Override
    public TransactionResponseInfo initiateTransaction(TransactionRequestInfo requestInfo) {
        String npciServerUrl = Constants.Servers.NPCI_Server.getTransactionURL();
        return restTemplate.postForEntity(npciServerUrl, requestInfo, TransactionResponseInfo.class).getBody();
    }
}
