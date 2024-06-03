package com.billion_dollor_company.Bank_Server.service.interfaces;

import com.billion_dollor_company.Bank_Server.payloads.AccountBasicDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.checkBalance.BalanceResDTO;
import com.billion_dollor_company.Bank_Server.payloads.fetchKeys.FetchKeysReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.fetchKeys.FetchKeysResDTO;
import com.billion_dollor_company.Bank_Server.payloads.registration.RegistrationReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.registration.RegistrationResDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.Bank_Server.payloads.transaction.TransactionResDTO;

public interface PSPService {
    TransactionResDTO initiateTransaction(TransactionReqDTO requestInfo);
    AccountBasicDTO getAccountInfo(AccountBasicDTO infoRequest);
    BalanceResDTO getAccountBalance(BalanceReqDTO infoRequest);
    RegistrationResDTO register(RegistrationReqDTO infoRequest);
    FetchKeysResDTO fetchKeys();
}
