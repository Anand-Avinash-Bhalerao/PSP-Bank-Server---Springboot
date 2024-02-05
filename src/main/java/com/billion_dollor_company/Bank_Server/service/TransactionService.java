package com.billion_dollor_company.Bank_Server.service;

import com.billion_dollor_company.Bank_Server.models.TransactionInfo;
public interface TransactionService {
    public String findEncryptedPasswordByPayeeUpiID(String upiId );
}
