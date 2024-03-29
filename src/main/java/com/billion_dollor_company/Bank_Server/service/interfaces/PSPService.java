package com.billion_dollor_company.Bank_Server.service.interfaces;

import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionRequestInfo;
import com.billion_dollor_company.Bank_Server.models.TransactionResponseInfo;
import org.springframework.http.ResponseEntity;

public interface PSPService {
    TransactionResponseInfo initiateTransaction(TransactionRequestInfo requestInfo);
    AccountInfo getUserInfo(AccountInfo infoRequest);
}
