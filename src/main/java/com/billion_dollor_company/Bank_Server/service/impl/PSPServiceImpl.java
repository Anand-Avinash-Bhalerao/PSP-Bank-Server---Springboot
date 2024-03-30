package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.DataNotFoundException;
import com.billion_dollor_company.Bank_Server.payloads.AccountBasicDTO;
import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseDTO;
import com.billion_dollor_company.Bank_Server.repository.AccountInfoRepository;
import com.billion_dollor_company.Bank_Server.service.interfaces.NpciApiService;
import com.billion_dollor_company.Bank_Server.service.interfaces.PSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PSPServiceImpl implements PSPService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private NpciApiService npciApiService;

    @Override
    public TransactionResponseDTO initiateTransaction(TransactionRequestDTO requestInfo) {
//        TransactionResponseInfo responseFromNpci = npciApiService.initiateTransaction(requestInfo);
//        return responseFromNpci;
        return npciApiService.initiateTransaction(requestInfo);
    }

    @Override
    public AccountBasicDTO getAccountInfo(AccountInfo infoRequest) {
        String upiID = infoRequest.getUpiID();
        AccountBasicDTO basicInfo = accountInfoRepository.getByUpiID(upiID);
        if (basicInfo == null) {
            throw new DataNotFoundException("The information for the account corresponding to upiID: " + infoRequest.getUpiID() + " was not found.");
        }
        return basicInfo;
    }
}
