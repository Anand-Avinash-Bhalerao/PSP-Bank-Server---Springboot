package com.billion_dollor_company.Bank_Server.service;

import com.billion_dollor_company.Bank_Server.dao.AccountDAO;
import com.billion_dollor_company.Bank_Server.models.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class AccountDetailsServiceImpl implements AccountDetailsService{

    @Autowired
    private AccountDAO accountDAO;
    @Transactional
    @Override
    public AccountInfo get(int id) {
        return accountDAO.get(id);
    }


    @Transactional
    @Override
    public List<AccountInfo> get() {
        return accountDAO.get();
    }

    @Transactional
    @Override
    public void save(AccountInfo info) {

    }
}
