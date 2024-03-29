package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.models.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionResponseInfo;
import com.billion_dollor_company.Bank_Server.service.interfaces.NpciApiService;
import com.billion_dollor_company.Bank_Server.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NpciApiServiceImpl implements NpciApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<TransactionResponseInfo> initiateTransaction(TransactionRequestInfo requestInfo) {

        String npciServerUrl = Constants.Servers.NPCI_Server.getTransactionURL();
        System.out.println("Here");
        // make the API call to NPCI.
        return restTemplate.postForEntity(npciServerUrl, requestInfo, TransactionResponseInfo.class);
    }
}
