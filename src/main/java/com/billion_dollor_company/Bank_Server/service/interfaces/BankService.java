package com.billion_dollor_company.Bank_Server.service.interfaces;

import com.billion_dollor_company.Bank_Server.payloads.TransactionRequestDTO;
import com.billion_dollor_company.Bank_Server.payloads.TransactionResponseDTO;

public interface BankService {
    TransactionResponseDTO initiateTransaction(TransactionRequestDTO requestInfo);
}
