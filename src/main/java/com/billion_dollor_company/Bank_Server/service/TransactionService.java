package com.billion_dollor_company.Bank_Server.service;
import com.billion_dollor_company.Bank_Server.util.Constants;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billion_dollor_company.Bank_Server.models.TransactionInfo;

import com.billion_dollor_company.Bank_Server.repository.TransactionInfoRepository;
import java.util.List;
@Service
public class TransactionService  {
    @Autowired
    private  TransactionInfoRepository repository;

    public  String getEncryptedPassword(String hashedPassword) {
        return repository.findByEncryptedPassword (hashedPassword);
    }

}
