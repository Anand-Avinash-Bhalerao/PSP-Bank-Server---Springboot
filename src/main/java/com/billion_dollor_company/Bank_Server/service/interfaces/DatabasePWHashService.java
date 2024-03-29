package com.billion_dollor_company.Bank_Server.service.interfaces;

public interface DatabasePWHashService {
    String getHashedPassword(String password);
}
