package com.billion_dollor_company.Bank_Server.models;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "TransactionRequest")
public class TransactionRequestInfo {
    private String payeeFullName;
    private String amountToTransfer;
    private String payeeAccountNo;
    private String payeeBankName;
    private String payerFullName;
    private String payerAccountNo;
    private String payerBankName;
    private String payerUpiID;
    private String encryptedPassword;
    private String payeeUpiID;
}
