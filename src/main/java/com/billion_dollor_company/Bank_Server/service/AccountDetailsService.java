package com.billion_dollor_company.Bank_Server.service;

import com.billion_dollor_company.Bank_Server.models.AccountInfo;

import java.util.List;

public interface AccountDetailsService {
    AccountInfo get(int id);

    List<AccountInfo> get();


    void save(AccountInfo info);
}
