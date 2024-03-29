package com.billion_dollor_company.Bank_Server.service.interfaces;

import com.billion_dollor_company.Bank_Server.models.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionResponseInfo;
import org.springframework.http.ResponseEntity;

public interface NpciApiService {
    public ResponseEntity<TransactionResponseInfo> initiateTransaction(TransactionRequestInfo requestInfo);
}
