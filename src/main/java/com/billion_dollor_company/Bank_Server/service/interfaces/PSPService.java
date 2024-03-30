package com.billion_dollor_company.Bank_Server.service.interfaces;

import com.billion_dollor_company.Bank_Server.payloads.AccountBasicDTO;
import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseDTO;

public interface PSPService {
    TransactionResponseDTO initiateTransaction(TransactionRequestDTO requestInfo);
    AccountBasicDTO getAccountInfo(AccountInfo infoRequest);
}
