package com.billion_dollor_company.Bank_Server.service.interfaces;

import com.billion_dollor_company.Bank_Server.payloads.AccountBasicInfo;
import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseInfo;

public interface PSPService {
    TransactionResponseInfo initiateTransaction(TransactionRequestInfo requestInfo);
    AccountBasicInfo getAccountInfo(AccountInfo infoRequest);
}
