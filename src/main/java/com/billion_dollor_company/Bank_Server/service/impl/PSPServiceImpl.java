package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionResponseInfo;
import com.billion_dollor_company.Bank_Server.repository.BankRepository;
import com.billion_dollor_company.Bank_Server.service.interfaces.NpciApiService;
import com.billion_dollor_company.Bank_Server.service.interfaces.PSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PSPServiceImpl implements PSPService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private NpciApiService npciApiService;

    @Override
    public TransactionResponseInfo initiateTransaction(TransactionRequestInfo requestInfo) {
        return npciApiService.initiateTransaction(requestInfo);
    }

    @Override
    public AccountInfo getUserInfo(AccountInfo infoRequest) {
        String upiID = infoRequest.getUpiID();
        return bankRepository.findByUpiID(upiID);
    }
}
