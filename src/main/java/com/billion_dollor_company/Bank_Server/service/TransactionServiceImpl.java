package com.billion_dollor_company.Bank_Server.service;

import com.billion_dollor_company.Bank_Server.models.TransactionInfo;
import com.billion_dollor_company.Bank_Server.repository.TransactionInfoRepository;

import java.util.Optional;

public class TransactionServiceImpl implements TransactionService{
    private TransactionInfoRepository transactionInfoRepository;

    public TransactionServiceImpl(TransactionInfoRepository transactionInfoRepository) {
        this.transactionInfoRepository = transactionInfoRepository;
    }

    @Override
    public String findEncryptedPasswordByPayeeUpiID(String upiId) {
        return transactionInfoRepository.findEncryptedPasswordByPayeeUpiID(upiId);
    }
}
