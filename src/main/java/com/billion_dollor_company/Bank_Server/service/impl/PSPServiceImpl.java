package com.billion_dollor_company.Bank_Server.service.impl;

import com.billion_dollor_company.Bank_Server.exceptions.customExceptions.DataNotFoundException;
import com.billion_dollor_company.Bank_Server.payloads.AccountBasicDTO;
import com.billion_dollor_company.Bank_Server.models.projections.AccountBasicProjection;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.listKeys.ListKeysReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;
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

    public PSPServiceImpl(AccountInfoRepository accountInfoRepository, NpciApiService npciApiService) {
        this.accountInfoRepository = accountInfoRepository;
        this.npciApiService = npciApiService;
    }

    @Override
    public AccountBasicDTO getAccountInfo(AccountBasicDTO infoRequest) {
        String upiID = infoRequest.getUpiID();
        AccountBasicProjection projection = accountInfoRepository.getBasicInfoByUpiID(upiID);
        if (projection == null) {
            throw new DataNotFoundException("The information for the account corresponding to upiID: " + infoRequest.getUpiID() + " was not found.");
        }
        return new AccountBasicDTO(projection);
    }

    @Override
    public BalanceResDTO getAccountBalance(BalanceReqDTO infoRequest) {
        return npciApiService.getAccountBalance(infoRequest);
    }


    @Override
    public TransactionResDTO initiateTransaction(TransactionReqDTO requestInfo) {
        return npciApiService.initiateTransaction(requestInfo);
    }


}
