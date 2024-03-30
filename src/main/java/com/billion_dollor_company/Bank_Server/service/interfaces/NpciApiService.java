package com.billion_dollor_company.Bank_Server.service.interfaces;

import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseInfo;

public interface NpciApiService {
    TransactionResponseInfo initiateTransaction(TransactionRequestInfo requestInfo);
}
