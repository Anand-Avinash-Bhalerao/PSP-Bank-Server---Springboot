package com.billion_dollor_company.Bank_Server.dao;

import com.billion_dollor_company.Bank_Server.models.AccountInfo;

import java.util.List;

public interface AccountDAO {
    AccountInfo get(int id);
    List<AccountInfo> get();

    void save(AccountInfo info);


    // getPassword(String upiID)
}
