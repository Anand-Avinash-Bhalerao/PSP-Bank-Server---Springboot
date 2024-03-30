package com.billion_dollor_company.Bank_Server.payloads;

public interface AccountBasicProjection {
    String getUpiID();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    String getAccountNo();

    String getMobileNo();
}
